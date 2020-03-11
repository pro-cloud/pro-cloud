package com.cloud.common.oauth.validate;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.oauth.exception.ValidateCodeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 抽象的图片验证码处理器
 * @author Aijm
 * @since 2019/5/26
 */
@Data
@AllArgsConstructor
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * 收集系统中所有的 {@link ValidateCodeProcessor} 接口的实现。
	 *
	 * 这是Spring开发的常见技巧，叫做定向查找（定向搜索）
	 *
	 * Spring启动时，会查找容器中所有的ValidateCodeGenerator接口的实现，并把Bean的名字作为key，放到map中
	 * 在我们这个系统中，ValidateCodeGenerator接口有两个实现，一个是ImageCodeGenerator，一个是SmsCodeGenerator，系统启动完成后，这个map中就会有2个bean，key分别是bean的名字
	 *
	 * 生成验证码的时候，会根据请求的不同（有一个type值区分是获取短信验证码还是图片验证码），来获取短信验证码的生成器或者图形验证码的生成器
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Autowired
	private ValidateCodeRepository validateCodeRepository;


	@Override
	public void create(ServletWebRequest request) throws Exception {
		// 生成
		C validateCode = generate(request);
		// 放到session
		save(request, validateCode);
		// 发送
		send(request, validateCode);
	}

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType().toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 *
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		validateCodeRepository.save(request, code, getValidateCodeType());
	}

	/**
	 * 发送校验码，由子类实现
	 *
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * 根据请求的url获取校验码的类型
	 * @return
	 */
	private ValidateCodeType getValidateCodeType() {
		String type = StrUtil.subBefore(getClass().getSimpleName(), "CodeProcessor", false);
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {

		ValidateCodeType codeType = getValidateCodeType();
		C codeInSession = (C) validateCodeRepository.get(request, codeType);
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					codeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}
		if (StrUtil.isBlank(codeInRequest)) {
			throw new ValidateCodeException(codeType + "验证码的值不能为空");
		}
		if (codeInSession == null) {
			throw new ValidateCodeException(codeType + "验证码不存在");
		}
		if (codeInSession.isExpried()) {
			validateCodeRepository.remove(request, codeType);
			throw new ValidateCodeException(codeType + "验证码已过期");
		}
		if (!StrUtil.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(codeType + "验证码不匹配");
		}

		validateCodeRepository.remove(request, codeType);

	}

}
