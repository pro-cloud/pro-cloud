package com.cloud.common.oauth.validate.image;


import com.alibaba.fastjson.JSON;
import com.cloud.common.oauth.validate.AbstractValidateCodeProcessor;
import com.cloud.common.oauth.validate.ValidateCodeGenerator;
import com.cloud.common.oauth.validate.ValidateCodeRepository;
import com.cloud.common.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 图片验证码处理器
 * @author Aijm
 * @since 2019/5/26
 */
@Component("imageValidateCodeProcessor")
@Slf4j
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


	public ImageCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		HttpServletResponse response = request.getResponse();
		response.reset();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSON.toJSONString(Result.success(imageCode.getImage())));
	}

}
