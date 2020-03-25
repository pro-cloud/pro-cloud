package com.cloud.auth.config;


import cn.hutool.core.convert.Convert;
import com.cloud.common.oauth.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cloud.common.oauth.properties.PermitProps;
import com.cloud.common.oauth.properties.SecurityConstants;
import com.cloud.common.oauth.validate.ValidateCodeSecurityConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 *  WebSecurityConfigurerAdapter 配置
 * @author Aijm
 * @since  2019/5/8
 */
@Slf4j
@Configuration
public class ProResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    protected AuthenticationSuccessHandler proAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler proAuthenticationFailureHandler;

    @Autowired
    private PermitProps permitProps;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(proAuthenticationSuccessHandler)
                .failureHandler(proAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .csrf().disable();
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
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
