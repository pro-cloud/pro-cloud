package com.cloud.common.oauth.properties;


import lombok.Data;

/**
 * @Author Aijm
 * @Description 验证码配置
 * @Date 2019/5/20
 */
@Data
public class ValidateCodeProps {

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProps image = new ImageCodeProps();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProps sms = new SmsCodeProps();
	/**
	 * 邮箱验证码配置
	 */
	private EmailCodeProps email = new EmailCodeProps();

}
