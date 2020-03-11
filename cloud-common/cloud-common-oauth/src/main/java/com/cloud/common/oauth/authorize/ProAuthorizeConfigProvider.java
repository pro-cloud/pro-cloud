package com.cloud.common.oauth.authorize;

import com.cloud.common.oauth.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @author Aijm
 * @since 2019/5/26
 */
@Component
@Order(Integer.MIN_VALUE)
public class ProAuthorizeConfigProvider implements AuthorizeConfigProvider {

	/**
	 * Config boolean.
	 *
	 * @param config the config
	 *
	 * @return the boolean
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(SecurityConstants.DEFAULT_SIGN_IN_URL_MOBILE,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*", "/token/**",
				"/druid/**", "/auth/**", "/assets/**", "/actuator/**", "/actuator", "/social/**", "/social").permitAll();
		return false;
	}

}
