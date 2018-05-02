package learn.redis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Redis分布式锁(使用redis做分布式锁的最佳方案)
 * 
 * 可靠性
 * 首先，为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下几个条件：
 * 1、互斥性。在任意时刻，只有一个客户端能持有锁。
 * 2、不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
 * 3、解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
 * 
 * 注：这里如果获取锁的线程故障，没有主动释放锁，只能等锁过期时间到了自动释放；
 * 分布式锁本身不应该含有这部分处理逻辑，因为设置过期时间已经保证了不会死锁；至于获得锁后因故障导致业务不会执行（或者执行一半）的问题，应该由业务方自己解决；
 * 
 * @author chaowang
 * @date 2018年3月28日
 */
public class DistributedLock {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    /**
     * 获取锁
     * @author chaowang
     * @date 2018年3月28日 下午2:19:35
     * @param lockKey
     * @param value : key的值可以用requestId，用来表示是谁加的锁
     * @param expire : 过期时间：单位秒
     * @return true:成功获得锁，false：获得锁失败
     */
    public static boolean lock(String lockKey,String value,int expire){
        Jedis jedis = pool.getResource();
        String lockResult = jedis.set(lockKey, value, "NX", "EX", expire);
        boolean result = false;
        if("OK".equals(lockResult)){
            result = true;
            System.out.println(Thread.currentThread().getName()+"加锁成功！");
        }else{
            System.out.println(Thread.currentThread().getName()+"加锁失败！锁已被占用！");
        }
        jedis.close();
        return result;
    }
    
    /**
     * 解锁
     * @author chaowang
     * @date 2018年3月28日 下午2:43:10
     * @param lockKey
     * @param requestId
     * @return
     */
    public static boolean unLock(String lockKey,String requestId){
        boolean result = false;
        Jedis jedis = pool.getResource();
        String luaScript = ""
                + "if(redis.call('get',KEYS[1])==ARGV[1])\n"
                + "then\n"
                +   "return redis.call('del',KEYS[1]);"
                +"else\n"
                +   "return 0\n"
                +   "end";
        //第一参数是脚本，第二参数KEYS列表，从第三个是ARGV列表（i从1开始）
        Object obj = jedis.eval(luaScript,Collections.singletonList(lockKey),Collections.singletonList(requestId));
        if(1==Integer.parseInt(obj.toString())){
            result = true;
            System.out.println(Thread.currentThread().getName()+"释放锁成功！");
        }else{
            System.out.println(Thread.currentThread().getName()+"释放锁失败：锁不存在或者不是自己加的锁！");
        }
        jedis.close();
        return result;
    }
    
    public static void main(String[] args) {
        final String lockKey = "testLockKey";
        final int exp = 1*60*5;
        
        int ThreadNum = 4;//设置成CPU核数
        
        //使用栅栏让多个线程一起开始抢占锁，如果是单核CPU，那这里的抢占测试没有意义，因为本身就是线程切换在执行，多核的才有可能多线程并行执行
        final CyclicBarrier cb  = new CyclicBarrier(ThreadNum,new Runnable() {
            public void run() {
                System.out.println("预备：开始抢...");
            }
        });
        Runnable r = new Runnable() {
            public void run() {
                String requestId = UUID.randomUUID().toString();
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                boolean lockSuccess = DistributedLock.lock(lockKey,requestId,exp);
                if(lockSuccess){
                    System.out.println(Thread.currentThread().getName()+"开始执行自己的逻辑！");
                    DistributedLock.unLock(lockKey, requestId);
                }
            }
        };
        
        for (int i = 0; i < ThreadNum; i++) {
            new Thread(r).start();
        }
        
    }
}
