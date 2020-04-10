package com.example.project2.redis;

import com.example.project2.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工厂bean
 *
 * @author chenhongde
 * @ClassName RedisPoolFactory
 * @date 2019/9/9 9:29
 */
@Component
public class RedisPoolFactory {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getPoolMaxTotal());
        poolConfig.setMinIdle(redisProperties.getPoolMinIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getPoolMaxWait() * 1000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        //当连接池内的连接耗尽时，getBlockWhenExhausted为true时，连接会阻塞，超过了阻塞的时间（设定的maxWaitMillis，单位毫秒）时会报错
        //poolConfig.setBlockWhenExhausted(true);
        //Idle时进行连接扫描
        poolConfig.setTestWhileIdle(true);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        poolConfig.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        JedisPool jp = new JedisPool(poolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout() * 1000, redisProperties.getPassword(), redisProperties.getDatabase());
        return jp;
    }
}
