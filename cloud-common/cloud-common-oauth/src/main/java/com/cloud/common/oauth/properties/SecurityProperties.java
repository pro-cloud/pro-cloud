package com.cloud.common.oauth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author Aijm
 * @Description 这里是一个 security 属性
 * @Date 2019/5/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "pro-cloud.security")
public class SecurityProperties {


	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();


	/**
	 * 验证码配置
	 */
	private ClientProperties client = new ClientProperties();


}

