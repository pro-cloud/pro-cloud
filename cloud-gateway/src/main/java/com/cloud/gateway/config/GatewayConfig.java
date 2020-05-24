package com.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * gateway 配置
 * @author Aijm
 * @since 2019/11/3
 */
@Configuration
public class GatewayConfig {

    /**
     *  根据uir进行限流。
     * @return
     */
    @Bean
    KeyResolver sysKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

    /**
     * 使用自己定义的限流类
     * @param redisTemplate
     * @param script
     * @param validator
     * @return
     */
    @Bean
    @Primary
    SysRedisRateLimiter sysRedisRateLimiter(
            ReactiveRedisTemplate<String, String> redisTemplate,
            @Qualifier(SysRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> script,
            Validator validator){
        return new SysRedisRateLimiter(redisTemplate , script , validator);
    }


}
