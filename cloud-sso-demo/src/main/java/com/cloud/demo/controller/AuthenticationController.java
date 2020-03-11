package com.cloud.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Aijm
 * @Description
 * @Date 2019/12/13
 */
@Slf4j
@RestController
public class AuthenticationController {

    @GetMapping("/getAuthentication")
    public Authentication getAuthentication(Authentication authentication) {
        OAuth2Authentication auth = (OAuth2Authentication)authentication;
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)auth.getDetails();
        log.info("token:{}", details.getTokenValue());
        log.info("name:{}", auth.getName());
        return authentication;
    }
}
