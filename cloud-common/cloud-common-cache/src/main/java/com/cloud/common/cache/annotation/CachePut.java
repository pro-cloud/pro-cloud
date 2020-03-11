package com.cloud.common.cache.annotation;


import com.cloud.common.cache.constants.CacheScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 存储缓存
 * @author Aijm
 * @since 2019/8/27
 */
// 在运行时可以获取
@Retention(RetentionPolicy.RUNTIME)
// 作用到方法
@Target(value = {ElementType.METHOD})
public @interface CachePut {

    /**
     * 缓存key menu_{0.id}{1}_type
     *
     * @return
     * @author Aijm
     * @date 2019/8/27
     */
    public String key() default "";

    /**
     * 作用域
     *
     * @return
     * @author Aijm
     * @date 2019/8/27
     */
    public CacheScope scope() default CacheScope.APPLICATION;

    /**
     * 过期时间36000s  10个小时后过期 0 表示永不过期
     *
     * @return
     * @author Aijm
     * @date 22019/8/27
     */
    public long expire() default 36000;

}
