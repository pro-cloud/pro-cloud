package com.cloud.auth.mapper;


import com.cloud.auth.entity.SysUser;
import com.cloud.common.data.base.ProMapper;

/**
 * 用户表
 *
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
public interface SysUserMapper extends ProMapper<SysUser> {

    /**
     *  根据手机号或者邮箱或者手机号登录
     * @param loginName
     * @return
     */
    public SysUser loginByName(String loginName);

    /**
     *  根据手机号登录
     * @param mobile
     * @return
     */
    public SysUser loginByPhone(String mobile);


    /**
     * QQ 登录
     * @param qqOpenid
     * @return
     */
    public SysUser loginByQQ(String qqOpenid);


    /**
     * 微信登录
     * @param wxOpenid
     * @return
     */
    public SysUser loginByWX(String wxOpenid);



}
