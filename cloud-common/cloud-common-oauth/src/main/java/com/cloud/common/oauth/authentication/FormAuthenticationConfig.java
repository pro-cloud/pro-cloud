package com.cloud.common.oauth.authentication;


import com.cloud.common.oauth.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 表单登录配置
 * @author Aijm
 * @since 2019/5/26
 */
@Component
public class FormAuthenticationConfig {

	@Autowired
	protected AuthenticationSuccessHandler proAuthenticationSuccessHandler;
	@Autowired
	protected AuthenticationFailureHandler proAuthenticationFailureHandler;

	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
			.successHandler(proAuthenticationSuccessHandler)
			.failureHandler(proAuthenticationFailureHandler);
	}

}
