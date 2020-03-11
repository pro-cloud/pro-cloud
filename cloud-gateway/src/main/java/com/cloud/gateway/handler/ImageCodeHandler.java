package com.cloud.gateway.handler;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import java.io.*;

/**
 * 验证码生成器
 * @author Aijm
 * @since 2019/5/12
 */
@Slf4j
@Component
@AllArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {


	@Override
	public Mono<ServerResponse> handle(ServerRequest serverRequest) {
		//生成验证码
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

		//图形验证码写出，可以写出到文件，也可以写出到流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		lineCaptcha.write(os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		InputStreamResource isr = new InputStreamResource(is);
		return ServerResponse
				.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(BodyInserters.fromResource(isr));

	}
}
