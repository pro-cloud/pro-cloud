package com.cloud.common.cache.annotation;

import com.cloud.common.cache.constants.LockKeys;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
 * @Author Aijm
 * @Description   基于redis的分布式锁注解
 * @Date 2019/8/19
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     * 默认锁的前缀
     * @return
     */
    String preKey() default LockKeys.PRE_DEFAULT_LOCK;
    /**
     * 分布式锁的键
     */
    String key() default LockKeys.DEFAULT_LOCK;

    /**
     * 锁释放时间，默认五秒
     */
    long timeOut() default LockKeys.DEFAULT_TIMEOUT;

    /**
     * 尝试在指定时间内加锁(可以理解为阻塞时间)
     * @return
     */
    long timeBlock() default LockKeys.DEFAULT_TIMEBLOCK;

    /**
     * 时间格式，默认：毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
