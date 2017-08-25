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
 * @author chaowang
 * @date 2017年8月18日
 */
public class WatchTest {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    public static void get(){
        Jedis jedis = pool.getResource();
        
        String watch = jedis.watch("aa");//watch key=aa的值
        System.out.println(Thread.currentThread().getName()+"---"+watch);
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
        
        jedis.unwatch();//不在watch
        
        pool.returnResource(jedis);
    }
    
    public static void main(String[] args) {
        get();
    }
}
