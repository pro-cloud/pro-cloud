package com.cloud.common.data.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 多租户属性配置
 * @author Aijm
 * @since 2020/1/14
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "pro-cloud.tenant")
public class ProTenantProps {

	/**
	 * 维护的默认租户列名称
	 */
	private String column = "tenant_id";

	/**
	 * 多租户的数据表集合
	 */
	private List<String> tables = new ArrayList<>();
}
