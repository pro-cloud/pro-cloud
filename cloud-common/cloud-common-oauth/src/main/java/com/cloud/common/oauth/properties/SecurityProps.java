package com.cloud.common.oauth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


/**
 * @Author Aijm
 * @Description 这里是一个 security 属性
 * @Date 2019/5/19
 */
@Data
@ConfigurationProperties(prefix = "pro-cloud.security")
@Configuration
@RefreshScope
public class SecurityProps {


	/**
	 * 验证码配置
	 */
	private ValidateCodeProps code = new ValidateCodeProps();


	/**
	 * 验证码配置
	 */
	private ClientProps client = new ClientProps();


}

