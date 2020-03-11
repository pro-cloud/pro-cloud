package com.cloud.common.cache.aspectj;

import com.cloud.common.cache.annotation.CachePut;
import com.cloud.common.cache.base.BaseCacheAspect;
import com.cloud.common.cache.redis.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Author Aijm
 * @Description 使用 aop 切面记录请求日志信息 尽量不要使用
 * @Date 2019/8/19
 */
@Aspect
@Component
@Order(20)
@Slf4j
public class CachePutAspect extends BaseCacheAspect {

    @Autowired
    private RedisDao redisDao;

    @Around("@annotation(cachePut)")
    public Object interceptor(ProceedingJoinPoint point, CachePut cachePut)
            throws Throwable {
        Object result = point.proceed();
        String key = getKey(point, cachePut.scope(), cachePut.key());
        putCache(redisDao, result, key, cachePut.expire());
        return result;
    }


}
