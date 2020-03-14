package com.cloud.common.swagger.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Aijm
 * @Description swagger 配置
 * @Date 2019/12/30
 */
@Data
@ToString
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProps {

	/**
	 * 是否开启swagger
	 */
	private Boolean enabled;

	/**
	 * 作者
	 **/
	private String author;

	/**
	 * 标题
	 **/
	private String title;
	/**
	 * 描述
	 **/
	private String desc;
	/**
	 * 版本
	 **/
	private String version;

	/**
	 * 服务条款
	 **/
	private String termsOfServiceUrl;

	/**
	 * host信息
	 **/
	private String host;

	/**
	 * 授权地址
	 */
	private String authUri;



}
