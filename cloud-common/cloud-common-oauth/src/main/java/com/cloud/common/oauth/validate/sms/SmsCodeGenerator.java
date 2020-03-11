package com.cloud.common.oauth.validate.sms;

import cn.hutool.core.util.RandomUtil;

import com.cloud.common.oauth.properties.SecurityProperties;
import com.cloud.common.oauth.validate.ValidateCode;
import com.cloud.common.oauth.validate.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 * @author Aijm
 * @since 2019/5/26
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 生成短信的验证码
	 * @param request
	 * @return
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomUtil.randomNumbers(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
