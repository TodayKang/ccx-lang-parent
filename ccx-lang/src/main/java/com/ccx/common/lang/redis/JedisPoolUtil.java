package com.ccx.common.lang.redis;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.ccx.common.lang.properties.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {

    private static final String REDIS_CONFIG_CLASS_PATH = "config-redis.properties";

    private static JedisPool jedisPool;

    static {
        if (jedisPool == null) {
            init();
        }
    }

    private static class SingletonHolder {
        private static Jedis instance = jedisPool.getResource();
    }

    public static Jedis getInstance() {
        return SingletonHolder.instance;
    }

    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private static void init() {
        // load the redis config properties
        Properties properties = PropertiesUtils.load(REDIS_CONFIG_CLASS_PATH);
        if (properties == null) {
            throw new NullPointerException("Could not load \"" + REDIS_CONFIG_CLASS_PATH + "\", please check! ");
        }

        // 配置 JedisPool

        // 是否采取默认的配置
        Boolean isDefaultConfig = PropertiesUtils.getBoolean(properties, "redis.isDefaultConfig");

        String host = PropertiesUtils.getString(properties, "redis.host");
        Integer port = PropertiesUtils.getInteger(properties, "redis.port");
        Integer timeout = PropertiesUtils.getInteger(properties, "redis.timeout");
        String password = PropertiesUtils.getString(properties, "redis.password");

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // 采取默认配置
        if (isDefaultConfig) {
            if (StringUtils.isBlank(password)) {
                jedisPool = new JedisPool(poolConfig, host, port, timeout);
            } else {
                jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
            }
        } else {
            // ------ 资源设置和使用 ------
            // 资源池确保最少空闲的连接数（默认0）
            Integer minIdle = PropertiesUtils.getInteger(properties, "redis.minIdle");

            // 资源池允许最大空闲的连接数（默认8）
            Integer maxIdle = PropertiesUtils.getInteger(properties, "redis.maxIdle");

            // 资源池中运行最大连接数（默认8；-1表示不限制）
            Integer maxTotal = PropertiesUtils.getInteger(properties, "redis.maxTotal");

            // 当资源池用尽后，调用者是否要等待（默认true，只有当为true时，maxWaitMillis 才会生效）
            Boolean blockWhenExhausted = PropertiesUtils.getBoolean(properties, "redis.blockWhenExhausted");

            // 当资源池连接用尽后，调用者的最大等待毫秒时间（默认-1，表示永不超时）
            // 如果超过等待时间（设置为阻塞时BlockWhenExhausted），则直接抛出 JedisConnectionException
            Integer maxWaitMillis = PropertiesUtils.getInteger(properties, "redis.maxWaitMillis");

            // 向资源池借用连接时是否做连接有效性检查(ping)，无效连接会被移除（默认false）
            Boolean testOnBorrow = PropertiesUtils.getBoolean(properties, "redis.testOnBorrow");

            // 向资源池归还连接时是否做连接有效性测试(ping)，无效连接会被移除（默认false）
            Boolean testOnReturn = PropertiesUtils.getBoolean(properties, "redis.testOnReturn");

            // 是否开启jmx监控，可用于监控（默认true）
            Boolean jmxEnabled = PropertiesUtils.getBoolean(properties, "redis.jmxEnabled");

            // ------ 空闲资源监测 ------
            // 资源池中资源最小空闲时间毫秒，达到此值后空闲资源将被移除（默认1800000，即30分钟，可默认 JedisPoolConfig 中配置）
            Integer minEvictableIdleTimeMillis = PropertiesUtils.getInteger(properties, "redis.minEvictableIdleTimeMillis");

            // 做空闲资源检测时，每次的采样数（默认3）
            Integer numTestsPerEvictionRun = PropertiesUtils.getInteger(properties, "redis.numTestsPerEvictionRun");

            // 是否开启空闲资源监测（默认false）
            Boolean testWhileIdle = PropertiesUtils.getBoolean(properties, "redis.testWhileIdle");

            // 空闲资源的检测周期毫秒数间隔（默认-1，表示不检测，建议设置，也可默认 JedisPoolConfig 中配置）
            Integer timeBetweenEvictionRunsMillis = PropertiesUtils.getInteger(properties, "redis.timeBetweenEvictionRunsMillis");

            if (minIdle != null) {
                poolConfig.setMinIdle(minIdle);
            }
            if (maxIdle != null) {
                poolConfig.setMaxIdle(maxIdle);
            }
            if (maxTotal != null) {
                poolConfig.setMaxTotal(maxTotal);
            }
            if (blockWhenExhausted != null) {
                poolConfig.setBlockWhenExhausted(blockWhenExhausted);
            }
            if (maxWaitMillis != null) {
                poolConfig.setMaxWaitMillis(maxWaitMillis);
            }
            if (testOnBorrow != null) {
                poolConfig.setTestOnBorrow(testOnBorrow);
            }
            if (testOnReturn != null) {
                poolConfig.setTestOnReturn(testOnReturn);
            }
            if (jmxEnabled != null) {
                poolConfig.setJmxEnabled(jmxEnabled);
            }
            if (minEvictableIdleTimeMillis != null) {
                poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            }
            if (numTestsPerEvictionRun != null) {
                poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
            }
            if (testWhileIdle != null) {
                poolConfig.setTestWhileIdle(testWhileIdle);
            }
            if (timeBetweenEvictionRunsMillis != null) {
                poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            }
            if (StringUtils.isBlank(password)) {
                jedisPool = new JedisPool(poolConfig, host, port, timeout);
            } else {
                jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
            }
        }

    }

}
