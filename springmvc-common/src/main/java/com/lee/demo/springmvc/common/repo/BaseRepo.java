package com.lee.demo.springmvc.common.repo;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Redis基础类
 *
 * Created by hzlifan on 2017/2/17.
 */
public class BaseRepo {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * kv插入
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取v
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除k
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 自增
     *
     * @param key
     */
    public void incr(String key) {
        redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 自减
     *
     * @param key
     */
    public void decr(String key) {
        redisTemplate.opsForValue().increment(key, -1);
    }

    /**
     * hash set
     *
     * @param tag
     * @param key
     * @param value
     */
    public void hset(String tag, String key, Object value) {
        redisTemplate.opsForHash().put(tag, key, value);
    }

    /**
     * hash get
     *
     * @param tag
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T hget(String tag, String key, Class<T> clazz) {
        return (T) redisTemplate.opsForHash().get(tag, key);
    }

    /**
     * hash delete
     *
     * @param tag
     * @param key
     */
    public void hdel(String tag, String key) {
        redisTemplate.boundHashOps(tag).delete(key);
    }

}
