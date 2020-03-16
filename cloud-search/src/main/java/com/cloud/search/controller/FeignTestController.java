package com.cloud.search.controller;

import com.cloud.admin.api.IUserService;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.data.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign")
public class FeignTestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="/{loginName}", method= RequestMethod.GET)
    public Result infoByName(@PathVariable("loginName") String loginName) {
        Result<SysUser> sysUserResult = userService.infoByName(loginName);
        return null;
    }
}
