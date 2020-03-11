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
     * 用户缓存过期时间（包含用户查询到的菜单角色等）
     */
    public static final Long USER_EXPIRETIME = 36000L;

}
