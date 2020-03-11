package com.cloud.common.oauth.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 * @author Aijm
 * @since 2019/5/26
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);

}
