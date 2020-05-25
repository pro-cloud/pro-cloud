package com.cloud.common.oauth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *  拦截租户异常
 * @author Aijm
 * @since 2019/5/19
 */
public class TentantException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public TentantException(String msg) {
		super(msg);
	}

}
