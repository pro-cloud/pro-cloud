package com.cloud.common.oauth.authorize;

import com.cloud.common.data.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 默认的授权配置管理器
 * @author Aijm
 * @since 2019/5/26
 */
@Component
public class ProAuthorizeConfigManager implements AuthorizeConfigManager {

	private final List<AuthorizeConfigProvider> authorizeConfigProviders;

	/**
	 * Instantiates a new Pc authorize config manager.
	 *
	 * @param authorizeConfigProviders the authorize config providers
	 */
	@Autowired
	public ProAuthorizeConfigManager(List<AuthorizeConfigProvider> authorizeConfigProviders) {
		this.authorizeConfigProviders = authorizeConfigProviders;
	}

	/**
	 * Config.
	 *
	 * @param config the config
	 */
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		boolean existAnyRequestConfig = false;
		String existAnyRequestConfigName = null;

		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
			if (existAnyRequestConfig && currentIsAnyRequestConfig) {
				throw new BaseException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
						+ authorizeConfigProvider.getClass().getSimpleName());
			} else if (currentIsAnyRequestConfig) {
				existAnyRequestConfig = true;
				existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
			}
		}

		if (!existAnyRequestConfig) {
			config.anyRequest().authenticated();
		}
	}

}
