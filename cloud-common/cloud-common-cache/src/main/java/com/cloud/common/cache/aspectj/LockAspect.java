package com.cloud.common.cache.aspectj;


import com.cloud.common.cache.annotation.RedisLock;
import com.cloud.common.cache.base.BaseCacheAspect;
import com.cloud.common.cache.constants.LockKeys;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.data.exception.BaseException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


/**
 * @Author Aijm
 * @Description 使用 aop 切面记录请求日志信息
 * @Date 2019/8/19
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LockAspect extends BaseCacheAspect {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @SneakyThrows
    @Around("@annotation(redisLock)")
    public Object interceptor(ProceedingJoinPoint point, RedisLock redisLock) {
        String key = getKey(point, redisLock);
        long timeOut = redisLock.timeOut();
        long time = TimeUnit.MILLISECONDS.convert(timeOut, redisLock.timeUnit());
        StringBuilder sbr = new StringBuilder(LockKeys.LOCK_REDIS);
        sbr.append(":").append(redisLock.preKey());
        log.info("锁的前缀为:{}, key为:{}", sbr.toString(), key);
        RedisLockRegistry lock = new RedisLockRegistry(redisConnectionFactory, sbr.toString(), time);
        Lock obtain = lock.obtain(key);

        if (obtain.tryLock(redisLock.timeBlock(), redisLock.timeUnit())) {
            try {
                log.info("获取到锁");
                return point.proceed();
            } finally {
                obtain.unlock();
            }
        } else {
            log.error("没有获取到锁");
            throw new BaseException(ResultEnum.CRUD_LOCK_OPERATE);
        }
    }


    /**
     * 获取redis锁key
     * @param point
     * @param redisLock
     * @return
     */
    private String getKey(ProceedingJoinPoint point, RedisLock redisLock) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        StringBuilder sbr = new StringBuilder();
        String[] paras= signature.getParameterNames();
        getElKey(point, redisLock.key(), sbr, paras);
        return sbr.toString();
    }

}
