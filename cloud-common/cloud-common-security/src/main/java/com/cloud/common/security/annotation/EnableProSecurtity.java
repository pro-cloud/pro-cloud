package com.cloud.common.security.annotation;



import com.cloud.common.security.config.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * security配置
 * @author Aijm
 * @since 2019/8/27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AutoConfiguration.class})
public @interface EnableProSecurtity {
}
