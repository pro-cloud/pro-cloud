package com.cloud.common.security.annotation;

import java.lang.annotation.*;

/**
 * @author Aijm
 * @date 2019/7/13
 * <p>
 * 服务调用鉴权注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inside {

}
