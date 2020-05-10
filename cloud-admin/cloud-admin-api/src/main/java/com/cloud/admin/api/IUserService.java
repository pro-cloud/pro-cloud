package com.cloud.admin.api;

import com.cloud.admin.beans.po.SysMenu;
import com.cloud.common.data.base.Result;
import com.cloud.common.util.client.CloudServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 获取当前用户信息
 * @author Aijm
 * @since 2020/5/8
 */
@FeignClient(contextId = "userService", value = CloudServiceList.CLOUD_ADMIN )
public interface IUserService {

    /**
     * 获取用户的权限信息(菜单)
     * @return
     */
    @RequestMapping(value="/menu/get/{userId}", method= RequestMethod.GET)
    public Result<List<SysMenu>> getMenuList(@PathVariable("userId") Long userId);
}
