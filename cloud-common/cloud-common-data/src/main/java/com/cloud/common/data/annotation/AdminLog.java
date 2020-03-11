package com.cloud.common.data.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author Aijm
 * @since 2019/10/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminLog {

	String value() default "操作日志";

}
