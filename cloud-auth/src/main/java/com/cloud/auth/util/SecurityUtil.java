package com.cloud.auth.util;

import com.cloud.common.oauth.security.SecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author Aijm
 * @Description 获取信息
 * @Date 2019/12/19
 */
@UtilityClass
public class SecurityUtil {


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
}
