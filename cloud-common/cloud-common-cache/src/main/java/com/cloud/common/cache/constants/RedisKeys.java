package com.cloud.common.cache.constants;

import lombok.experimental.UtilityClass;

/**
 * @Author Aijm
 * @Description  redis 锁用到的前缀和后缀存放静态类
 * @Date 2019/12/3
 */
@UtilityClass
public class RedisKeys {

    ////////////////////////////      默认初始化常量 //////////////////////////
    /**
     * redis锁  固定前缀:前缀:key 中的前缀 中的固定前缀
     */
    public static final String LOCK_REDIS = "lock_redis";

    /**
     * redis  固定前缀:前缀:key 中的前缀 (中的固定前缀:前缀)
     */
    public static final String LOCK_REDIS_DEFAULT = "lock_redis:application";

    /**
     * 固定前缀:前缀:key 中的前缀
     */
    public static final String PRE_DEFAULT_LOCK = "application";


    /**
     * 默认锁的的key
     */
    public static final String DEFAULT_LOCK = "'redis'";

    /**
     * 默认redis锁过期时间 5秒
     */
    public static final long DEFAULT_TIMEOUT = 5000L;

    /**
     * 默认redis锁阻塞时间 5秒
     */
    public static final long DEFAULT_TIMEBLOCK = 5000L;


    /////////////////////////////////// 自定义key ////////////////////////////////


    /**
     * 注册或者修改用户信息锁
     */
    public static final String USER_ADD_UPDATE = "user:add_update";


    /**
     * 租户锁 和自增主键
     */
    public static final String TENTANT_ADD = "user:tentant";

    /**
     * 租户hash
     */
    public static final String TENTANT_ADD_HASH = "tentant";



}
