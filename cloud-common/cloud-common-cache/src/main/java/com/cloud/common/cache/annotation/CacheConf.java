package com.cloud.common.cache.annotation;

import com.cloud.common.cache.constants.CacheScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 放入头部
 * @author Aijm
 * @since 2019/8/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface CacheConf {

    public CacheScope scope() default CacheScope.APPLICATION;
}
