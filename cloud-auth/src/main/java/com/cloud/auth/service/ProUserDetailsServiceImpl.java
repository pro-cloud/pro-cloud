package com.cloud.auth.service;


import com.cloud.auth.constant.StatusVar;
import com.cloud.auth.entity.SysUser;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.data.exception.BaseException;
import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.oauth.service.ProUserDetailsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *  实现 ProUserDetailsService
 * @author Aijm
 * @since 2019/8/31
 */
@Slf4j
@Service
public class ProUserDetailsServiceImpl implements ProUserDetailsService {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByMoblie(String mobile) {
        SysUser user = sysUserService.loginByPhone(mobile);
        if (user == null) {
            log.info("{}手机号找不到注册用户", mobile);
            throw new BaseException(ResultEnum.LOGIN_NAME);
        }
        if (StatusVar.STATUS_DOWN.equals(user.getStatus())) {
            log.info("{}用户名被禁用", mobile);
            throw new BaseException();
        }
        return new SecurityUser(mobile, user.getPassword(), user.getId(),
                user.getUserType(), user.getName(), String.valueOf(user.getTenantId()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        SysUser user = sysUserService.loginByName(username);
        if (user == null) {
            log.info("{}用户名找不到注册用户", username);
            throw new BaseException(ResultEnum.LOGIN_NAME);
        }
        if (StatusVar.STATUS_DOWN.equals(user.getStatus())) {
            log.info("{}用户名被禁用", username);
            throw new BaseException(ResultEnum.LOGIN_LOCK);
        }
        return new SecurityUser(username, user.getPassword(), user.getId(),
                user.getUserType(), user.getName(), String.valueOf(user.getTenantId()));
    }

    /**
     * QQ 登录
     *
     * @param qqOpenid
     * @return
     */
    @Override
    public UserDetails loginByQQ(String qqOpenid) {

        SysUser user = sysUserService.loginByQQ(qqOpenid);
        if (user == null) {
            log.info("{}qqOpenid找不到注册用户", qqOpenid);
            throw new UsernameNotFoundException("用户名异常");
        }
        return new SecurityUser(user.getLoginName(), user.getPassword(), user.getId(),
                user.getUserType(), user.getName(), String.valueOf(user.getTenantId()));
    }

    /**
     * 微信登录
     *
     * @param wxOpenid
     * @return
     */
    @Override
    public UserDetails loginByWX(String wxOpenid) {

        SysUser user = sysUserService.loginByWX(wxOpenid);
        if (user == null) {
            log.info("{}wxOpenid找不到注册用户", wxOpenid);
            throw new UsernameNotFoundException("用户名异常");
        }
        return new SecurityUser(user.getLoginName(), user.getPassword(), user.getId(),
                user.getUserType(), user.getName(), String.valueOf(user.getTenantId()));
    }
}
