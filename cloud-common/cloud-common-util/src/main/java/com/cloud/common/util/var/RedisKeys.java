package com.cloud.common.util.var;

import lombok.experimental.UtilityClass;

/**
 * @Author Aijm
 * @Description 存储redis的所有key
 * @Date 2019/7/23
 */
@UtilityClass
public class RedisKeys {


    /**
     * 用来存储oauth的token
     */
    public static final String REDIS_TOKEN_KEY = "pro:redis:token:";
}
