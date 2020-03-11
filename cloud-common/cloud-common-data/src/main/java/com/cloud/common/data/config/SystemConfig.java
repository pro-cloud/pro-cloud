package com.cloud.common.data.config;

import com.cloud.common.data.user.SystemService;
import com.cloud.common.data.user.impl.SystemServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 配置bean
 * @author Aijm
 * @since 2019/8/25
 */
@Configuration
public class SystemConfig {

    /**
     * SystemService 实现
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SystemService.class)
    public SystemService systemService() {
        return new SystemServiceImpl();
    }


    /**
     * 默认密码处理器
     *
     * @return 密码加密器
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
