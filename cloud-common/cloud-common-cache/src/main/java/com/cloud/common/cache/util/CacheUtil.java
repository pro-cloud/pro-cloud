package com.cloud.common.cache.util;

import com.cloud.common.cache.constants.CacheKeys;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.redis.RedisDao;
import com.cloud.common.data.util.SpringUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author Aijm
 * @Description  缓存工具类 建议使用 该工具类查询出来的数据不可能存在‘${NULL}’ 为null的数据
 * @Date 2019/8/29
 */
@Slf4j
@UtilityClass
public class CacheUtil extends RedisUtil{

    /**
     * 为空时的字符串
     */
    private static final String NULL_EMPTY = CacheKeys.EMPTY_OBJ;

    /**
     * 为 null 的过期时间
     */
    private static final Long EXPIRETIME_NULL = CacheKeys.EXPIRETIME_NULL;


    /**
     * 写入APPLICATION_CACHE缓存
     * @param key
     * @param value
     */
    public static void putNull(String key, Object value) {
        putNull(APPLICATION_CACHE, key, value);
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void putNull(String cacheName, String key, Object value) {
        if (value == null) {
            redisDao.vSet(getKey(cacheName, key), NULL_EMPTY,EXPIRETIME_NULL);
        } else {
            redisDao.vSet(getKey(cacheName, key), value);
        }
    }

    /**
     * 写入缓存 含有过期时间
     * @param cacheName
     * @param key
     * @param value
     */
    public static void putNull(String cacheName, String key, Object value, Long expireTime) {
        if (value == null) {
            redisDao.vSet(getKey(cacheName, key), NULL_EMPTY,EXPIRETIME_NULL);
        } else {
            redisDao.vSet(getKey(cacheName, key), value, expireTime);
        }
    }
}
