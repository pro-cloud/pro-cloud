package com.cloud.common.cache.aspectj;

import com.cloud.common.cache.annotation.CacheClear;
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
 * @Description 使用 aop 切面记录请求日志信息
 * @Date 2019/8/19
 */
@Aspect
@Component
@Order(10)
@Slf4j
public class CacheClearAspect extends BaseCacheAspect {

    @Autowired
    private RedisDao redisDao;


    @Around("@annotation(cacheClear)")
    public Object interceptor(ProceedingJoinPoint point, CacheClear cacheClear)
            throws Throwable {
        String key = getKey(point, cacheClear.scope(), cacheClear.key());
        if (!cacheClear.pattern()) {
            redisDao.delete(key);
        } else {
            redisDao.deletePattern(key);
        }
        return point.proceed();
    }


}
