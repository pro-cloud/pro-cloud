package com.cloud.gateway.config;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.validation.constraints.Min;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 自定义限流
 * @author Aijm
 * @since 2019/11/3
 */
public class SysRedisRateLimiter extends AbstractRateLimiter<SysRedisRateLimiter.Config> implements ApplicationContextAware {

    public static final String REDIS_SCRIPT_NAME = "redisRequestRateLimiterScript";

    public static final String REPLENISH_RATE_KEY = "replenishRate";

    public static final String BURST_CAPACITY_KEY = "burstCapacity";

    /**
     * 处理速度
     */
    private static final String DEFAULT_REPLENISHRATE="default.replenishRate";
    /**
     * 容量
     */
    private static final String DEFAULT_BURSTCAPACITY="default.burstCapacity";

    public static final String CONFIGURATION_PROPERTY_NAME = "redis-rate-limiter";
    public static final String REMAINING_HEADER = "X-RateLimit-Remaining";
    public static final String REPLENISH_RATE_HEADER = "X-RateLimit-Replenish-Rate";
    public static final String BURST_CAPACITY_HEADER = "X-RateLimit-Burst-Capacity";
    private Log log = LogFactory.getLog(this.getClass());
    private ReactiveRedisTemplate<String, String> redisTemplate;
    private RedisScript<List<Long>> script;
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private Config defaultConfig;
    private boolean includeHeaders = true;
    private String remainingHeader = REMAINING_HEADER;
    private String replenishRateHeader = REPLENISH_RATE_HEADER;
    private String burstCapacityHeader = BURST_CAPACITY_HEADER;

    public SysRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate, RedisScript<List<Long>> script, Validator validator) {
        super(Config.class, CONFIGURATION_PROPERTY_NAME, validator);
        this.redisTemplate = redisTemplate;
        this.script = script;
        this.initialized.compareAndSet(false, true);
    }

    public SysRedisRateLimiter(int defaultReplenishRate, int defaultBurstCapacity) {
        super(Config.class, CONFIGURATION_PROPERTY_NAME, (Validator)null);
        this.defaultConfig = new Config()
                .setReplenishRate(defaultReplenishRate)
                .setBurstCapacity(defaultBurstCapacity);
    }

    static List<String> getKeys(String id) {
        String prefix = "request_rate_limiter.{" + id;
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    public boolean isIncludeHeaders() {
        return this.includeHeaders;
    }

    public void setIncludeHeaders(boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
    }

    public String getRemainingHeader() {
        return this.remainingHeader;
    }

    public void setRemainingHeader(String remainingHeader) {
        this.remainingHeader = remainingHeader;
    }

    public String getReplenishRateHeader() {
        return this.replenishRateHeader;
    }

    public void setReplenishRateHeader(String replenishRateHeader) {
        this.replenishRateHeader = replenishRateHeader;
    }

    public String getBurstCapacityHeader() {
        return this.burstCapacityHeader;
    }

    public void setBurstCapacityHeader(String burstCapacityHeader) {
        this.burstCapacityHeader = burstCapacityHeader;
    }

    private RateLimiterConfig rateLimiterConf;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.rateLimiterConf = context.getBean(RateLimiterConfig.class);

    }

    Config getDefaultConfig() {
        return this.defaultConfig;
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (!this.initialized.get()) {
            throw new IllegalStateException("RedisRateLimiter is not initialized");
        } else {
            if (ObjectUtil.isEmpty(rateLimiterConf) ){
                throw new IllegalArgumentException("No Configuration found for route " + routeId);
            }
            //获取的是自定义的map
            Map<String , Integer> rateLimitMap = rateLimiterConf.getRateLimitMap();
            //缓存的key
            String replenishRateKey = routeId + "." + id + "." + REPLENISH_RATE_KEY;
            //若map中不存在则采用默认值，存在则取值。
            int replenishRate = ObjectUtil.isEmpty(rateLimitMap.get(replenishRateKey)) ? rateLimitMap.get(DEFAULT_REPLENISHRATE) : rateLimitMap.get(replenishRateKey);
            //容量key
            String burstCapacityKey = routeId + "." + id + "." + BURST_CAPACITY_KEY;
            //若map中不存在则采用默认值，存在则取值。
            int burstCapacity = ObjectUtil.isEmpty(rateLimitMap.get(burstCapacityKey)) ? rateLimitMap.get(DEFAULT_BURSTCAPACITY) : rateLimitMap.get(burstCapacityKey);

            try {
                List<String> keys = getKeys(id);
                List<String> scriptArgs = Arrays.asList(replenishRate + "", burstCapacity + "", Instant.now().getEpochSecond() + "", "1");
                Flux<List<Long>> flux = this.redisTemplate.execute(this.script, keys, scriptArgs);
                return flux.onErrorResume((throwable) -> {
                    return Flux.just(Arrays.asList(1L, -1L));
                }).reduce(new ArrayList(), (longs, l) -> {
                    longs.addAll(l);
                    return longs;
                }).map((results) -> {
                    boolean allowed = (Long)results.get(0) == 1L;
                    Long tokensLeft = (Long)results.get(1);
                    Response response = new Response(allowed, this.getHeaders(replenishRate , burstCapacity, tokensLeft));
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("response: " + response);
                    }

                    return response;
                });
            } catch (Exception var9) {
                this.log.error("Error determining if user allowed from redis", var9);
                return Mono.just(new Response(true, this.getHeaders(replenishRate , burstCapacity , -1L)));
            }
        }
    }


    public Map<String, String> getHeaders(Integer replenishRate, Integer burstCapacity, Long tokensLeft) {
        Map<String, String> headers = new HashMap();
        if (this.isIncludeHeaders()) {
            headers.put(this.remainingHeader, tokensLeft.toString());
            headers.put(this.replenishRateHeader, String.valueOf(replenishRate));
            headers.put(this.burstCapacityHeader, String.valueOf(burstCapacity));
        }

        return headers;
    }

    @Validated
    public static class Config {
        @Min(1L)
        private int replenishRate;
        @Min(1L)
        private int burstCapacity = 1;

        public Config() {
        }

        public int getReplenishRate() {
            return this.replenishRate;
        }

        public Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }

        public int getBurstCapacity() {
            return this.burstCapacity;
        }

        public Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }

        @Override
        public String toString() {
            return "Config{replenishRate=" + this.replenishRate + ", burstCapacity=" + this.burstCapacity + '}';
        }
    }
}
