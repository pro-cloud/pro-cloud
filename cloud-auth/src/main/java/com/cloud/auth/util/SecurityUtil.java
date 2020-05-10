package com.cloud.auth.util;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.data.util.ServletUtil;
import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.util.util.StrUtils;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aijm
 * @Description 获取信息
 * @Date 2019/12/19
 */
@UtilityClass
public class SecurityUtil {

    public static final String BEARER_TOKEN_TYPE = "Bearer ";

    /**
     * 获取Authentication
     * @return
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取用户信息
     * @param authentication
     * @return
     */
    public SecurityUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
       return principal instanceof  SecurityUser ? ((SecurityUser) principal) : null;
    }

    /**
     * 获取用户信息
     * @return
     */
    public SecurityUser getUser() {
        Authentication authentication = getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal instanceof  SecurityUser ? ((SecurityUser) principal) : null;
    }

    /**
     * 获取用户token
     * @return
     */
    public static String getToken() {
        HttpServletRequest request = ServletUtil.getRequest();
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotBlank(header) && header.startsWith(BEARER_TOKEN_TYPE)) {
            return StrUtils.removePrefix(header, BEARER_TOKEN_TYPE);
        }
        return null;
    }
}
