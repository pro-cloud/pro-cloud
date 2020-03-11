package com.cloud.admin.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.interfaces.UserService;
import com.cloud.admin.service.SysUserService;
import com.cloud.common.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *  暴露的接口
 * @author Aijm
 * @since 2019/8/31
 */
@RestController
public class UserController implements UserService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result<SysUser> infoByName(@PathVariable("loginName") String loginName) {
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getLoginName, loginName));
        return Result.success(sysUser);
    }

    @Override
    public Result<SysUser> infoByMobile(@PathVariable("mobile") String mobile) {
        return null;
    }
}
