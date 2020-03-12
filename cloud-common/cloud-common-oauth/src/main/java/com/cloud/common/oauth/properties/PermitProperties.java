package com.cloud.common.oauth.properties;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;


/**
 * @author Aijm
 * @date 2019/5/13
 * <p>
 * 资源服务器对外直接暴露URL,如果设置contex-path 要特殊处理
 */
@Slf4j
@Configuration
@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.oauth2.client")
public class PermitProperties {

	@Getter
	@Setter
	private List<String> ignoreUrls = new ArrayList<>();


}
