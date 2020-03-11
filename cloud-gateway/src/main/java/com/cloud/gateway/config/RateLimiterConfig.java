package com.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  限流配置 使用配置文件的方式进行初始化
 * @author Aijm
 * @since 2019/10/31
 */
@Data
@Component
@ConfigurationProperties(prefix = "ratelimiter-config")
public class RateLimiterConfig {
    /**
     * 处理速度
     */
    private static final String DEFAULT_REPLENISHRATE="default.replenishRate";
    /**
     * 容量
     */
    private static final String DEFAULT_BURSTCAPACITY="default.burstCapacity";

    private Map<String , Integer> rateLimitMap = new ConcurrentHashMap<String , Integer>(){
        {
            put(DEFAULT_REPLENISHRATE , 10);
            put(DEFAULT_BURSTCAPACITY , 100);
        }
    };



}
