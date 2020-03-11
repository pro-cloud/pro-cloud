
package com.cloud.common.oauth.validate.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 * @author Aijm
 * @since 2019/5/26
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {


	/**
	 * 发送短信接口
	 *
	 * @param mobile
	 * @param code
	 */
	@Override
	public void send(String mobile, String code) {
		log.info("向手机{}发送短信验证码:{}",  mobile, code);
	}
}
