package com.cloud.common.oauth.handler;



import com.alibaba.fastjson.JSON;
import com.cloud.common.data.exception.BaseException;
import com.cloud.common.oauth.exception.ValidateCodeException;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * APP环境下认证失败处理器
 * @author Aijm
 * @since 2019/5/27
 */
@Slf4j
@Component("proAuthenctiationFailureHandler")
public class ProAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json;charset=UTF-8");
		log.info("登录失败,失败处理器");
		if (exception instanceof ValidateCodeException) {
			response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.LOGIN_CODE)));
		} else if (exception instanceof UsernameNotFoundException) {
			response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.LOGIN_NAME)));
		} else if (exception instanceof BadCredentialsException) {
			response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.LOGIN_PASSWORD)));
		} else {
			log.info("异常信息:", exception.getMessage(), exception);
			Throwable cause = exception.getCause();
			if (cause instanceof BaseException){
				response.getWriter().write(JSON.toJSONString(Result.error(((BaseException) cause).getCode(),cause.getMessage())));
			} else {
				response.getWriter().write(JSON.toJSONString(Result.error("登录失败")));
			}

		}



	}

}
