package com.springboot.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author skyer
 * @date 2018/8/21 15:09
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /******************************String******************************************/

    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }


    public boolean set(String key, int timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, timeout, timeout);
        return true;
    }


    public boolean set(String key, Object value, int timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        return true;
    }


    public boolean setIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }


    public boolean multiSet(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
        return true;
    }


    public Object getAndSet(String key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }


    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public boolean delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
        return true;
    }

    /***************************************Hash***************************************/

    public boolean put(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return true;
    }


    public boolean putIfAbsent(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
        return true;
    }


    public boolean putAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }


    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    public Object get(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    public Object entries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    public boolean delete(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
        return true;
    }


    public Object keys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }


    public Object values(String key) {
        return redisTemplate.opsForHash().values(key);
    }


    public Object size(String key) {
        return redisTemplate.opsForHash().size(key);
    }


    public Object multiGet(String key, Collection<String> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }
}
