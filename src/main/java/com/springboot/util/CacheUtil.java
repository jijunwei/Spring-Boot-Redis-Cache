package com.springboot.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public class CacheUtil {

    public static boolean forHashPutIfAbsent(RedisTemplate redisTemplate, String lockKey, String lockHashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(lockKey, lockHashKey, value);
    }

    public static void forHashDel(RedisTemplate redisTemplate, String lockKey, String lockHashKey) {
        redisTemplate.opsForHash().delete(lockKey, lockHashKey);
    }

    public static boolean hasKey(RedisTemplate redisTemplate, String key) {
        return redisTemplate.hasKey(key);
    }

    public static void putValue(RedisTemplate redisTemplate, String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void setExpire(RedisTemplate redisTemplate, String key, int timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }


    public static void putValueWithExpire(RedisTemplate redisTemplate, String key, Object value, int timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }


}
