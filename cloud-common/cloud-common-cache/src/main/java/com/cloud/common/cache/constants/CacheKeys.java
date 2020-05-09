package com.cloud.common.cache.constants;


import lombok.experimental.UtilityClass;

/**
 * cache 的key
 * @author Aijm
 * @since 2019/8/29
 */
@UtilityClass
public class CacheKeys {


    /**
     * 为null 时的字符串
     */
    public static final String EMPTY_OBJ = "${null}";

    /**
     * 为null 时的过期时间
     */
    public static final Long EXPIRETIME_NULL = 60L;


    /**
     * 用户缓存过期时间 默认 s 10小时
     */
    public static final Long DEFAULT_EXPIRETIME = 36000L;

}
