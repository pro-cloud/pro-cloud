package com.cloud.admin.config;

import com.cloud.admin.componse.PermissionServiceImpl;
import com.cloud.common.security.component.IPermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Aijm
 * @Description admin 暴露模块出去的配置
 * @Date 2020/5/9
 */
@Configuration
public class AdminConfig {

    /**
     * 配置默认的权限配置
     * @return
     */
    @Bean(name = "pms")
    @ConditionalOnMissingBean(IPermissionService.class)
    public IPermissionService pms() {
        return new PermissionServiceImpl();
    }
}
