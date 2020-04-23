package com.cloud.auth.config;

import com.cloud.auth.component.Auth2ResponseExceptionTranslator;
import com.cloud.auth.service.ProJdbcClientDetailsService;
import com.cloud.common.util.var.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 *  AuthorizationServerConfigurerAdapter 配置
 * @author Aijm
 * @since  2019/5/8
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ProJdbcClientDetailsService jdbcClientDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("proUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Value("${security.oauth2.reuseRefreshToken:false}")
    private boolean reuseRefreshToken;

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(RedisKeys.REDIS_TOKEN_KEY);
        return tokenStore;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


    /**
     * 读取数据库中所有的client
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients.withClientDetails(jdbcClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(customTokenEnhancer)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(reuseRefreshToken)
                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
                .exceptionTranslator(new Auth2ResponseExceptionTranslator());
    }





}
