package com.cloud.auth.config;


import cn.hutool.core.convert.Convert;
import com.cloud.common.oauth.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cloud.common.oauth.properties.PermitProps;
import com.cloud.common.oauth.properties.SecurityConstants;
import com.cloud.common.oauth.service.ProUserDetailsService;
import com.cloud.common.oauth.validate.ValidateCodeFilter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;



/**
 *  WebSecurityConfigurerAdapter 配置
 * @author Aijm
 * @since  2019/5/8
 */
@Slf4j
@Configuration
public class ProResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired(required = false)
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired(required = false)
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired(required = false)
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private PermitProps permitProps;

    @Autowired(required = false)
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .csrf().disable();
        // 添加拦截器
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
        String[] urls = Convert.toStrArray(permitProps.getIgnoreUrls());
        http.authorizeRequests().antMatchers(urls).permitAll();
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, ProUserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
