package learn.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
/**
 * redis调用lua脚本例子
 * redis不需要特殊安装什么，默认安装的redis就带了lua的解释引擎
 * redis版本3.0.6
 * 
 * redis的脚本调用是原子操作，也就在在脚本执行过程中不会执行其他线程发送的命令，直到脚本执行结束！
 * 脚本如果执行时间过程，会阻塞其他线程范围redis
 * @author chaowang
 * @date 2017年8月18日
 */
public class LuaRedisTest {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,Protocol.DEFAULT_TIMEOUT,"wangchao");
    
    public static void get(){
        Jedis jedis = pool.getResource();
        
        String redbagCntKey = "redbagCnt";//redis中存放红包数量的key；测试时需要现在redis中设置redbagCnt的值
        String luaScript = ""
                +"local cnt = redis.call('get',KEYS[1])\n"
                + "if(not cnt)\n"
                + "then\n"
                +   "return nil;"
                + "end\n"
                +"local result = nil\n"
                +"if(tonumber(cnt)>0)\n"
                +"then\n"
                +   "result = redis.call('DECR',KEYS[1])\n"
                +"else\n"
                +   "result = nil\n"
                +"end\n"
                +"return result\n";
        //第一参数是脚本，第二参数是脚本中的参数个数，从第三个参数开始之后都是传到脚本中的参数，在脚本中通过KEYS[i]获取参数，i从1开始
        Object obj = jedis.eval(luaScript,1,redbagCntKey);
        if(obj==null){
            System.out.println("红包已抢光！");
        }else{
            System.out.println("抢到红包！剩余红包数量："+obj);
        }
        
        pool.returnResource(jedis);
    }
    
    public static void main(String[] args) {
        get();
    }
}
