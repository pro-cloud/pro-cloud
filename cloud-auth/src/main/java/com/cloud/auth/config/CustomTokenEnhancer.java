package com.cloud.auth.config;


import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.util.oauth.AdditionalToken;
import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 定制 TokenEnhancer
 * @author Aijm
 * @since 2019/5/15
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser user = (SecurityUser)authentication.getPrincipal();
        final Map<String, Object> additionalInfo = Maps.newHashMap();
        additionalInfo.put(AdditionalToken.MAKE_BY, AdditionalToken.MAKER);
        additionalInfo.put(AdditionalToken.USER_ID, user.getUserId());
        additionalInfo.put(AdditionalToken.USER_TYPE, user.getUserType());
        additionalInfo.put(AdditionalToken.USER_NAME, user.getName());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
