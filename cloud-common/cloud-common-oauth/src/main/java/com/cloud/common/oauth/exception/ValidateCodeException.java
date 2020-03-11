package com.cloud.common.oauth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 校验异常
 * @author Aijm
 * @since 2019/5/19
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 7375658553307326998L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
