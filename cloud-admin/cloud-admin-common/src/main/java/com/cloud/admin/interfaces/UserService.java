package com.cloud.admin.interfaces;


import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.util.base.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户信息
 * @author Aijm
 * @since 2019/5/11
 */
public interface UserService {

    /**
     * 获取指定用户全部信息 根据用户名
     * @param loginName 用户登录名
     * @return
     */
    @RequestMapping(value="/user/info/{loginName}", method= RequestMethod.GET)
    public Result<SysUser> infoByName(@PathVariable("loginName") String loginName);


    /**
     * 获取指定用户全部信息 根据手机号
     * @param mobile
     * @return
     */
    @RequestMapping(value="/user/info/phone/{mobile}", method= RequestMethod.GET)
    public Result<SysUser> infoByMobile(@PathVariable("mobile") String mobile);


}
