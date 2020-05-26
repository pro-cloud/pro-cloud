package com.cloud.common.security.util;

import com.cloud.common.security.component.SecurityUser;
import com.cloud.common.util.var.StaticVar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author Aijm
 * @Description 根据SecurityContext 获取信息
 * @Date 2020/3/26
 */
@Slf4j
public class SecurityUtil {


    /**
     * 判断该用户是不是超级管理员 并给admin赋值
     * @param id 用户id
     * @return
     */
    public static boolean hasAdmin(Long id){
        return id != null && (id== 1);
    }

    /**
     * 判断该用户是不是超级管理员 并给admin赋值
     * @return
     */
    public static boolean hasAdmin(){
        Long id = getUserId();
        return id != null && (id== 1);
    }

    /**
     * 获取SecurityUser
     * @return
     */
    public static SecurityUser getSecurityUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (SecurityUser)authentication.getPrincipal();
    }

    /**
     * 判断该用户是否登录
     * @return
     */
    public static boolean hasAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }


    /**
     * 获取登录用户的信息
     * @return
     */
    public static Long getUserId(){
        try {
            return getSecurityUser().getUserId();
        } catch (Exception e) {
            log.info("没有登录", e);
        }
        return StaticVar.DEFAULT_USERID;
    }


    /**
     * 获取登录用户的管理租户信息,多个租户 第一个为本身租户
     * @return
     */
    public static String getTenantIds(){
        return getSecurityUser().getTenantIds();
    }
    /**
     * 获取登录用户的信息 登录名
     * @return
     */
    public static String getUserName(){
        return getSecurityUser().getUsername();
    }
}
