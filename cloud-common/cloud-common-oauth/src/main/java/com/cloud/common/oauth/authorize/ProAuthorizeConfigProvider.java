package com.cloud.common.oauth.authorize;

import cn.hutool.core.convert.Convert;
import com.cloud.common.oauth.properties.PermitProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @author Aijm
 * @since 2019/5/26
 */
@Component
@Order(Integer.MIN_VALUE)
public class ProAuthorizeConfigProvider implements AuthorizeConfigProvider {


	@Autowired
	private PermitProps permitProps;

	/**
	 * Config boolean.
	 *
	 * @param config the config
	 * @return 返回的boolean表示配置中是否有针对anyRequest的配置 。在整个授权配置中， 应该有且仅有一个针对anyRequest的配置，如果所有的实现都没有针对anyRequest的配置， 系统会自动增加一个anyRequest().authenticated()的配置。如果有多个针对anyRequest 的配置，则会抛出异常。
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		String[] urls = Convert.toStrArray(permitProps.getIgnoreUrls());
		config.antMatchers(urls).permitAll();
		return false;
	}



}
