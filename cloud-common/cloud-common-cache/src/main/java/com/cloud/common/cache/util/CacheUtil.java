package com.cloud.common.cache.util;

import com.cloud.common.cache.constants.CacheKeys;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.redis.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author Aijm
 * @Description  缓存工具类 建议使用 该工具类查询出来的数据不可能存在‘${NULL}’ 为null的数据
 * @Date 2019/8/29
 */
@Slf4j
@Component
public class CacheUtil {

    private static RedisDao redisDao;

    @Autowired
    CacheUtil(RedisDao redisDao) {
        CacheUtil.redisDao = redisDao;
    }

    /**
     * 默认缓存存放地址
     */
    private static final String APPLICATION_CACHE = CacheScope.APPLICATION.getCacheName();

    /**
     * 为空时的字符串
     */
    private static final String NULL_EMPTY = CacheKeys.EMPTY_OBJ;

    /**
     * 为 null 的过期时间
     */
    private static final Long EXPIRETIME_NULL = CacheKeys.EXPIRETIME_NULL;


    /**
     * 获取APPLICATION_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(APPLICATION_CACHE, key);
    }


    /**
     * 写入APPLICATION_CACHE缓存
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        put(APPLICATION_CACHE, key, value);
    }

    /**
     * 写入APPLICATION_CACHE缓存 含有过期时间
     * @param key
     * @param value
     * @param expireTime
     */
    public static void put(String key, Object value, Long expireTime) {
        put(APPLICATION_CACHE, key, value, expireTime);
    }
    /**
     * 从APPLICATION_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        RedisUtil.remove(APPLICATION_CACHE, key);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Object obj = redisDao.vGet(getKey(cacheName, key));
        if (NULL_EMPTY.equals(obj.toString())) {
            return null;
        } else {
            return redisDao.vGet(getKey(cacheName, key));
        }
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
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
    public static void put(String cacheName, String key, Object value, Long expireTime) {
        if (value == null) {
            redisDao.vSet(getKey(cacheName, key), NULL_EMPTY,EXPIRETIME_NULL);
        } else {
            redisDao.vSet(getKey(cacheName, key), value, expireTime);
        }
    }

    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        RedisUtil.remove(cacheName, key);
    }

    /**
     * 从cacheName缓存中移除所有
     * @param cacheName
     */
    public static void removeCacheName(String cacheName) {
       RedisUtil.removeCacheName(cacheName);
    }

    /**
     * 从缓存中移除所有 批量删除key(key值可为模糊匹配---sysUser:office:* <---> *代表任意字符)
     * @param pattern
     */
    public static void removeAll(String pattern) {
        RedisUtil.removeAll(pattern);
    }


    /**
     * 获取缓存键名，多数据源下增加数据源名称前缀
     * @param key
     * @return
     */
    private static String getKey(String cacheName, String key){
        StringBuilder sbr = new StringBuilder(cacheName);
        sbr.append(":").append(key);
        return sbr.toString();
    }

}
