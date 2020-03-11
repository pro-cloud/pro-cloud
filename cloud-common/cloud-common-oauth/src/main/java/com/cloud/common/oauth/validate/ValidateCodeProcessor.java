package com.cloud.common.oauth.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author Aijm
 * @Description 校验码处理器，封装不同校验码的处理逻辑
 * @Date 2019/5/20
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 *
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 *
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
