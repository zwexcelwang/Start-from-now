package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
加载配置文件，配置连接池参数
提供获取连接的方法
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream inputStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties properties = new Properties();
        //关联文件

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));

        //初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));


    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
