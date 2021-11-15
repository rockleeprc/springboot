package com.hualala.client.service;

import com.hualala.client.util.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存操作层
 */
@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HashOperations hashOperations;


    /**
     * 从redis 获取 hash 类型数据
     *
     * @param key    健
     * @param params 参数拼接字符串
     * @param clazz  返回值类型
     * @param <T>    返回值
     * @return
     */
    public <T> T hashGet(String key, String params, Class<T> clazz) {
        // 校验key field是否存在
        boolean hasKey = hasKey(key, params);
        if (!hasKey) return null;

        String murHash = HashUtils.murmur128(params);
        Map<String, T> values = (Map<String, T>) hashOperations.get(key, murHash);
        if (values != null) {
            // 没有crash
            if (values.size() == 1) {
                Set<Map.Entry<String, T>> entries = values.entrySet();
                for (Map.Entry<String, T> entry : entries) {
                    return entry.getValue();
                }
            }
            // 有crash
            return values.get(HashUtils.sha256(params));
        }
        return null;
    }


    /**
     * 缓存数据到 redis，默认expire时间24L
     *
     * @param key    健
     * @param params 参数拼接字符串
     * @param value  值
     * @see com.hualala.client.service.CacheService#hashPut(java.lang.String, java.lang.String, java.lang.Object, java.lang.Long, java.util.concurrent.TimeUnit)
     */
    public void hashPutDefaultExpire(String key, String params, Object value) {
        hashPut(key, params, value, CacheConstants.HASH_EXPIRE_HOUR, TimeUnit.HOURS);
    }

    /**
     * 缓存数据到 redis
     *
     * @param key     健
     * @param params  参数拼接字符串
     * @param value   值
     * @param timeout 失效时间
     * @param unit    失效时间单位
     * @see CacheService#hashPut(java.lang.String, java.lang.String, java.util.Map, java.lang.Long, java.util.concurrent.TimeUnit)
     */
    public void hashPut(String key, String params, Object value, Long timeout, TimeUnit unit) {
        String murmur128 = HashUtils.murmur128(params);
        String sha256 = HashUtils.sha256(params);
        Map<String, Object> values;
        //  field 存在，取出map 保存value
        Boolean hasKey = hashOperations.hasKey(key, murmur128);
        if (hasKey) {
            values = (Map<String, Object>) hashOperations.get(key, murmur128);
            Objects.requireNonNull(values);
            values.put(sha256, value);
            hashPut(key, murmur128, values, timeout, unit);
            return;
        }
        // field 不存在，实例化map 保存value
        values = new HashMap<>();
        values.put(sha256, value);
        hashPut(key, murmur128, values, timeout, unit);
    }

    /**
     * 缓存hash 类型数据到redis
     *
     * @param key     健
     * @param hash    参数murmur128
     * @param value   值
     * @param timeout 失效时间
     * @param unit    失效时间单位
     */
    private void hashPut(String key, String hash, Map<String, Object> value, Long timeout, TimeUnit unit) {
        hashOperations.put(key, hash, value);
        expire(key, timeout, unit);
    }

    private void expire(String key, Long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    private boolean hasKey(String key, String params) {
        String murHash = HashUtils.murmur128(params);
        return hashOperations.hasKey(key, murHash);
    }

    static class CacheConstants {
        public static final Long HASH_EXPIRE_HOUR = 24L;
    }
}
