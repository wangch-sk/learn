package learn.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.Transaction;

/**
 * redis watch测试
 * 在睡眠期间，手动修改redis中被watch的值，来模拟在程序运行中被其他线程修改时的情形
 * 
 * 在此思考一个问题：事务期间是否一直占有着redis线程，导致其他线程无法访问redis？经过测试，结论是：不会
 * @author chaowang
 * @date 2017年8月18日
 */
public class WatchTest {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    public static void set(){
        Jedis jedis = pool.getResource();
        
        String watchKey = "aa";
        String watch = jedis.watch(watchKey);//watch key=aa的值
        System.out.println(Thread.currentThread().getName()+"---watch---"+watch);
        Transaction trans = jedis.multi();
        trans.set("bb", "222");
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        trans.set("aa", "222");
        
        List<Object> result = trans.exec();//但aa被更改时，trans.exec()返回值为null
        for (Object resp : result) {
            System.out.println(resp);
        }
//        jedis.unwatch();//可以不需要，因为 EXEC 命令会执行事务，因此 WATCH 命令的效果已经产生了；而 DISCARD 命令在取消事务的同时也会取消所有对 key 的监视，因此这两个命令执行之后，就没有必要执行 UNWATCH 了
        
        jedis.close();
    }
    
    public static void get(){
        Jedis jedis = pool.getResource();
        System.out.println("cc="+jedis.get("cc"));
        jedis.close();
    }
    
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                WatchTest.set();
            }
        }).start();
        Thread.sleep(100);
        get();
    }
}
