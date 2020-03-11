package com.cloud.common.oauth.properties;

import lombok.Data;

/**
 * @Author Aijm
 * @Description sms code 属性
 * @Date 2019/5/20
 */
@Data
public class SmsCodeProperties {

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
	/**
	 * 每天每个手机号最大送送短信数量
	 */
	private int mobileMaxSendCount = 10;
	/**
	 * 每天每个IP最大送送短信数量
	 */
	private int ipMaxSendCount = 10;
	/**
	 * 每天最大送送短信数量
	 */
	private int totalMaxSendCount = 1000;


}
