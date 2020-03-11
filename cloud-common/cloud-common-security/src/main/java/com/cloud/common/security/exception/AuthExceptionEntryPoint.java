package com.cloud.common.security.exception;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpStatus;
import com.cloud.common.util.base.Result;
import com.cloud.common.util.enums.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义Token异常信息
 * @author Aijm
 * @since 2019/11/20
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		log.error("AuthExceptionEntryPoint:",authException.getMessage());
		response.setCharacterEncoding(CharsetUtil.UTF_8);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		Result<Object> result = Result.error(ResultEnum.TOKEN_PAST);
		if (authException != null) {
			result.setData(authException.getMessage());
		}
		response.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}
}
