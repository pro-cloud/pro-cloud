package com.cloud.common.oauth.authentication;


import com.cloud.common.oauth.service.DefaultUserDetailsServiceImpl;
import com.cloud.common.oauth.service.ProUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置
 * @author Aijm
 * @since 2019/5/27
 */
@Configuration
public class AuthenticationBeanConfig {


	/**
	 * 默认认证器
	 *
	 * @return user details service
	 */
	@Bean
	@ConditionalOnMissingBean(ProUserDetailsService.class)
	public ProUserDetailsService userDetailsService() {
		return new DefaultUserDetailsServiceImpl();
	}


}
