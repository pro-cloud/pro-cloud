package com.cloud.auth.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



/**
 * @Author Aijm
 * @Description 认证失败后的处理
 * @Date 2019/12/21
 */
@Slf4j
@Component
public class ProAuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {


	/**
	 * 登录失败
	 * @param event
	 */
	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		Authentication authentication = event.getAuthentication();
		AuthenticationException exception = event.getException();
		log.info("用户:{} 登录异常:{}", authentication.getPrincipal(), exception.getLocalizedMessage());
	}
}
