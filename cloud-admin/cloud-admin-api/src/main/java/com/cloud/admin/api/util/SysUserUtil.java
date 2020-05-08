package com.cloud.admin.api.util;

import com.cloud.admin.api.IUserService;
import com.cloud.admin.beans.po.SysMenu;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.security.util.SecurityUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 提供工具类
 * @author Aijm
 * @since 2020/5/8
 */
@Slf4j
@UtilityClass
public class SysUserUtil extends SecurityUtil {

    private static IUserService userService = SpringUtil.getBean(IUserService.class);

    /**
     * 获取某用户id 菜单信息
     * @param userId
     * @return
     */
    public static List<SysMenu> getMenuList(Long userId){
        return (List<SysMenu>)userService.getMenuList(userId).getData();
    }

    /**
     * 获取当前用户授权菜单
     * @return
     */
    public static List<SysMenu> getMenuList(){
        Long userId = getUserId();
        return getMenuList(userId);
    }
}
