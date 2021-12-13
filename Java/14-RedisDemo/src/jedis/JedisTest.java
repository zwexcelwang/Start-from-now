package jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.JedisPoolUtils;

public class JedisTest {


    public void test1(){
        Jedis jedis = new Jedis("localhost", 6379); //如果使用空参构造，默认值就是"localhost", 6379
        jedis.set("username", "zhangsan");
        //将activecode：hehe键值对存入redis中，并且20秒后自动删除该键值对
        jedis.setex("activecode", 20, "hehe");
        jedis.close();

    }


    @Test
    public void test2(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        //创建连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        Jedis jedis = jedisPool.getResource();

        jedis.set("hehe", "hehe");

        //关闭，归还到连接池中
        jedis.close();

    }

    @Test
    public void test3() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("hello", "world");
        System.out.println(jedis.get("hello"));
        jedis.close();
    }


}
