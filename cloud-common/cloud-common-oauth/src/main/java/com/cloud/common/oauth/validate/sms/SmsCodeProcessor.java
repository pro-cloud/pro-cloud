package com.cloud.common.oauth.validate.sms;


import com.cloud.common.oauth.exception.ValidateCodeException;
import com.cloud.common.oauth.properties.SecurityConstants;
import com.cloud.common.oauth.validate.AbstractValidateCodeProcessor;
import com.cloud.common.oauth.validate.ValidateCode;
import com.cloud.common.oauth.validate.ValidateCodeGenerator;
import com.cloud.common.oauth.validate.ValidateCodeRepository;
import com.cloud.common.util.base.Result;

import com.cloud.common.util.enums.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 短信验证码处理器
 * @author Aijm
 * @since 2019/5/26
 */
@Component("smsValidateCodeProcessor")
@Slf4j
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {


	@Resource
	private SmsCodeSender smsCodeSender;
	@Resource
	private ObjectMapper objectMapper;

	public SmsCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	/**
	 * 发送校验码，由子类实现
	 *
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		Result result = Result.success("校验成功");
		// 统一处理短信流量
		try {
			smsCodeSender.send(mobile, validateCode.getCode());
		} catch (ValidateCodeException e) {
			log.error("校验短信数量, e={}", e.getMessage(), e);
			result = Result.error(ResultEnum.LOGIN_CODE);
		} catch (Exception e) {
			log.error("对校验短信数量进行判断, e={}", e.getMessage(), e);
			result = Result.error("对校验短信数量进行判断异常");
		}
		String json = objectMapper.writeValueAsString(result);
		HttpServletResponse response = request.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
