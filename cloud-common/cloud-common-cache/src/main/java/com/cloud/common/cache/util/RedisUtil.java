package com.cloud.common.cache.util;

import com.cloud.common.cache.constants.CacheKeys;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.redis.RedisDao;
import com.cloud.common.data.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Aijm
 * @Description redis 处理缓存 和cacheUtil的区别是缓存存储了null的区别
 * @Date 2019/8/29
 */
@Slf4j
public class RedisUtil {

    protected static RedisDao redisDao = SpringUtil.getBean(RedisDao.class);

    /**
     * 默认缓存存放地址
     */
    protected static final String APPLICATION_CACHE = CacheScope.APPLICATION.getCacheName();


    /**
     * 获取APPLICATION_CACHE缓存
     * @param key
     * @return
     */
    public static Long hmSetIncrement(String key, String hashKey, Long value) {
        return redisDao.hmSetIncrement(key, hashKey, value);
    }
    /**
     * 获取APPLICATION_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(APPLICATION_CACHE, key);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        return redisDao.vGet(getKey(cacheName, key));
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
     * 写入APPLICATION_CACHE缓存
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        put(APPLICATION_CACHE, key, value);
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        redisDao.vSet(getKey(cacheName, key), value);
    }

    /**
     * 写入APPLICATION_CACHE缓存 含有过期时间
     * @param key
     * @param value
     */
    public static void putTime(String key, Object value) {
        putTime(APPLICATION_CACHE, key, value, CacheKeys.DEFAULT_EXPIRETIME);
    }


    /**
     * 写入缓存 含有过期时间
     * @param cacheName
     * @param key
     * @param value
     */
    public static void putTime(String cacheName, String key,
                               Object value) {
        putTime(cacheName, key, value, CacheKeys.DEFAULT_EXPIRETIME);
    }

    /**
     * 写入缓存 含有过期时间
     * @param cacheName
     * @param key
     * @param value
     * @param expireTime
     */
    public static void putTime(String cacheName, String key,
                Object value, Long expireTime) {
        redisDao.vSet(getKey(cacheName, key), value, expireTime);
    }

    /**
     * 从APPLICATION_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(APPLICATION_CACHE, key);
    }
    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        redisDao.delete(getKey(cacheName, key));
    }

    /**
     * 从cacheName缓存中移除所有
     * @param cacheName
     */
    public static void removeCacheName(String cacheName) {
        StringBuilder sbr = new StringBuilder(cacheName);
        sbr.append(":*");
        redisDao.deletePattern(sbr.toString());
        log.info("清理缓存： {}", cacheName);
    }

    /**
     * 从缓存中移除所有 批量删除key(key值可为模糊匹配---sysUser:office:* <---> *代表任意字符)
     * @param pattern
     */
    public static void removeAll(String pattern) {
        redisDao.deletePattern(pattern);
        log.info("清理缓存： {}", pattern);
    }

    /**
     * 获取缓存键名，多数据源下增加数据源名称前缀
     * @param key
     * @return
     */
    protected static String getKey(String cacheName, String key){
        StringBuilder sbr = new StringBuilder(cacheName);
        sbr.append(":").append(key);
        return sbr.toString();
    }

}
