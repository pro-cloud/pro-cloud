package com.cloud.common.oauth.validate.image;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import com.cloud.common.oauth.properties.SecurityProps;
import com.cloud.common.oauth.validate.ValidateCodeGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码生成器
 * @author Aijm
 * @since 2019/5/26
 */
@Slf4j
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

	/**
	 * 系统配置
	 */
	@Autowired
	private SecurityProps securityProps;

	@Override
	public ImageCode generate(ServletWebRequest request) {
		// 首先从请求参数中获取验证码的宽度，如果没有则使用配置的值
		// 这里是实现了验证码参数的三级可配：请求级>应用级>默认配置
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				securityProps.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				securityProps.getCode().getImage().getHeight());

		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(width, height,
				securityProps.getCode().getImage().getLength(), 50);
		//图形验证码写出，可以写出到文件，也可以写出到流
		String image = lineCaptcha.getImageBase64();
		//输出code
		log.info("验证码:{}", lineCaptcha.getCode());

		return new ImageCode(image, lineCaptcha.getCode(), securityProps.getCode().getImage().getExpireIn());
	}



}
