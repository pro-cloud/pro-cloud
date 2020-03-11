package com.cloud.auth.handler;

import com.cloud.common.oauth.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aijm
 * @Description 认证成功后的处理
 * @Date 2019/12/21
 */
@Slf4j
@Component
public class ProAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 * @param request        请求
	 * @param response       返回
	 */
	@Override
	public void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		log.info("登录成功! 用户：{} ", authentication.getPrincipal());
	}
}
