package com.cloud.common.security.component;


import com.cloud.common.util.oauth.AdditionalToken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义 UserAuthenticationConverter
 * @author Aijm
 * @since 2019/7/28
 */
@Service
public class ProUserAuthenticationConverter extends DefaultUserAuthenticationConverter {


    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(AdditionalToken.LONGIN_NAME)) {
            Object principal = map.get(AdditionalToken.LONGIN_NAME);
            Object obj = map.get(AdditionalToken.USER_ID);
            Long userId = Long.valueOf(String.valueOf(obj));
            Object objType = map.get(AdditionalToken.USER_TYPE);
            Object name = map.get(AdditionalToken.USER_NAME);
            Object tenantIds = map.get(AdditionalToken.TENANT_IDS);
            SecurityUser user = new SecurityUser((String) principal, "", userId,
                    String.valueOf(objType), String.valueOf(name), String.valueOf(tenantIds));
            return new UsernamePasswordAuthenticationToken(user, "N/A", AuthorityUtils.NO_AUTHORITIES);
        } else {
            return null;
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get("authorities");
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
        } else if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection)authorities));
        } else {
            throw new IllegalArgumentException("Authorities must be either a String or a Collection");
        }
    }
}
