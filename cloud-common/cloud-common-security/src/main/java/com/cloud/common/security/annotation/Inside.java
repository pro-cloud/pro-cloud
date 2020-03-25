package com.cloud.common.security.annotation;

import java.lang.annotation.*;

/**
 * @author Aijm
 * @date 2019/7/13
 * 改注解 主要是为了解决没有必要的校验token
 *  比如内部用户直接为最高权限
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inside {

}
