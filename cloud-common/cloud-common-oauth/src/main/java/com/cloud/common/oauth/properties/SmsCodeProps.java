package com.cloud.common.oauth.properties;

import lombok.Data;

/**
 * @Author Aijm
 * @Description sms code 属性
 * @Date 2019/5/20
 */
@Data
public class SmsCodeProps {

	/**
	 * 验证码长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = 60;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;


}
