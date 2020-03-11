package com.cloud.common.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author Aijm
 * @Description userDetailsService 接口再次封装
 * @Date 2019/5/20
 */
public interface ProUserDetailsService extends UserDetailsService {

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    UserDetails loadUserByMoblie(String mobile);


    /**
     * QQ 登录
     * @param qqOpenid
     * @return
     */
    UserDetails loginByQQ(String qqOpenid);


    /**
     * 微信登录
     * @param wxOpenid
     * @return
     */
    UserDetails loginByWX(String wxOpenid);
}
