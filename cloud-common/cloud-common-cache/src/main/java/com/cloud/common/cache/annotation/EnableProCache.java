package com.cloud.common.cache.annotation;


import com.cloud.common.cache.config.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * 缓存配置
 * @author Aijm
 * @since 2019/8/27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AutoConfiguration.class})
public @interface EnableProCache {
}
