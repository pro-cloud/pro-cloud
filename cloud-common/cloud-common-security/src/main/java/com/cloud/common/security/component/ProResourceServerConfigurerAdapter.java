package com.cloud.common.security.component;


import cn.hutool.core.convert.Convert;
import com.cloud.common.security.exception.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Aijm
 * @Description 资源服务器
 * @Date 2019/5/8
 */
@Configuration
@RefreshScope
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ProResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProUserAuthenticationConverter proUserAuthenticationConverter;

    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    private PermitProps permitProps;

    @Value("${security.oauth2.resourceId}")
    private String resourceId;


    /**
     * 配置校验token方式
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(proUserAuthenticationConverter);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        remoteTokenServices.setRestTemplate(restTemplate);
        resources.tokenServices(remoteTokenServices);
        resources.authenticationEntryPoint(authExceptionEntryPoint);
        resources.resourceId(resourceId);
        super.configure(resources);
    }


    /**
     * 对匹配的资源进行放行
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] urls = Convert.toStrArray(permitProps.getIgnoreUrls());
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers(urls).permitAll();
        http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();

    }
}
