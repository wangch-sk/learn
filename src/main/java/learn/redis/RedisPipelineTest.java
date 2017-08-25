package learn.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Protocol;

/**
 * redis管道测试
 * @author chaowang
 * @date 2017年8月18日
 */
public class RedisPipelineTest {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    public static void get(){
        Jedis jedis = pool.getResource();
        Pipeline pipelined = jedis.pipelined();
        pipelined.get("aa");
        pipelined.get("bb");
        
        List<Object> result = pipelined.syncAndReturnAll();//也可以用pipelined.sync,但这个需要定义一个List<Response<String>>，通过result.add(pipelined.get("aa"));将结果放到定义的list中
        
        //打印结果
        for (Object response : result) {
            System.out.println(response);
        }
        
        pool.returnResource(jedis);
    }
    
    public static void main(String[] args) {
        get();
    }
}
