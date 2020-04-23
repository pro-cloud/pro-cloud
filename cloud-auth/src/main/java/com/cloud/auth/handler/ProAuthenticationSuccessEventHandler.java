package com.cloud.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


/**
 * @Author Aijm
 * @Description 认证成功后的处理
 * @Date 2019/12/21
 */
@Slf4j
@Component
public class ProAuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {


	/**
	 *  事件处理成功
	 * @param event
	 */
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Authentication authentication = event.getAuthentication();
		log.info("登录成功! 用户：{} ", authentication.getPrincipal());
	}
}
