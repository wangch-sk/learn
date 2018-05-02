package learn.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * 测试redis内存淘汰策略
 * redis.conf中# maxmemory <bytes>参数，未开启或者设置成0,表示对内存使用没有限制
 * @author chaowang
 * @date 2018年3月25日
 */
public class MaxMemoryTest {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    public static void fill(int n){
        Jedis jedis = pool.getResource();
        
        String key = "test_";
        for (int i = 0; i < n; i++) {
            jedis.set(key+i, "11111"+i);
        }
        
        jedis.close();
        System.out.println("填充完成！");
    }
    
    public static void main(String[] args) {
        fill(1000);
    }
}
