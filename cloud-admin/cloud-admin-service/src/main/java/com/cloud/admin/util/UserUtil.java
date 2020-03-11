package com.cloud.admin.util;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.dto.OfficeDTO;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysMenu;
import com.cloud.admin.beans.po.SysOffice;
import com.cloud.admin.beans.po.SysRole;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.datascope.DataScope;
import com.cloud.admin.service.SysMenuService;
import com.cloud.admin.service.SysRoleService;
import com.cloud.admin.service.SysUserService;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.security.component.SecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 *  用户工具类  不使用service的原因是 防止初夏
 * @author Aijm
 * @since 2019/8/25
 */
@UtilityClass
public class UserUtil {


    private static SysMenuService sysMenuService = SpringUtil.getBean(SysMenuService.class);

    private static SysRoleService sysRoleService = SpringUtil.getBean(SysRoleService.class);

    private static SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);



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

    //////////////////  菜单相关  /////////////////////////

    /**
     * 获取当前用户授权菜单
     * @return
     */
    public static List<SysMenu> getMenuList(){
        Long userId = getUserId();
        return sysMenuService.findByUserId(userId);
    }
    ///////////////////////用户相关 //////////////////////////////////
    /**
     * 获取当前用户详细信息 不包括角色 和部门信息
     * @return
     */
    public static SysUser getUser(){
        Long userId = getUserId();
        return sysUserService.getById(userId);
    }

    /**
     * 获取登录用户的信息
     * @return
     */
    public static Long getUserId(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        SecurityUser user = (SecurityUser)authentication.getPrincipal();
        return user.getUserId();
    }


    /**
     * 获取登录用户的信息 登录名
     * @return
     */
    public static String getUserName(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        SecurityUser user = (SecurityUser)authentication.getPrincipal();
        return user.getUsername();
    }
    /**
     * 获取用户详细 信息 包含 部门信息和角色信息
     * @return
     */
    public static UserDTO getUserDTO(){
        UserDTO userDTO = new UserDTO();
        // 获取用户信息
        SysUser sysUser = getUser();
        BeanUtil.copyProperties(sysUser, userDTO);
        // 获取用户部门信息
        SysOffice userOffice = OfficeUtil.getUserOffice();
        OfficeDTO officeDTO = new OfficeDTO();
        BeanUtil.copyProperties(userOffice, officeDTO);
        userDTO.setOffice(officeDTO);
        // 获取角色信息
        userDTO.setRoleList(getRoleList());
        return userDTO;
    }

    /////////////////////////  角色相关 ////////////////////////////
    /**
     * 获取当前用户的角色信息
     * @return
     */
    public static List<RoleDTO> getRoleList(){
        return  sysRoleService.findList(getUser());
    }


    /**
     * 获取当前用户的角色信息
     * @return
     */
    public static List<RoleDTO> getRoleList(Long userId){
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        return  sysRoleService.findList(getUser());
    }

    /**
     * 获取所有的角色 不包含菜单
     * @return
     */
    public static List<SysRole> getRoleAll(){
        return sysRoleService.list(Wrappers.emptyWrapper());
    }

    /**
     * 获取到最大的 数据权限
     *  数值越小 数据权限最大 默认最小权限数字为4
     * @return
     */
    public static int getMaxDataScope(){
        // 获取到最大的数据权限范围
        int dataScopeInteger = DataScope.DATA_SCOPE_SELF;
        for (RoleDTO r : UserUtil.getRoleList()){
            if (r.getDataScope() < dataScopeInteger){
                dataScopeInteger = r.getDataScope();
            }
        }
        return dataScopeInteger;
    }


    /**
     * 获取到最大的 数据权限  可以将角色中某个角色权限放大放小
     *   数值越小 数据权限最大 默认最小权限数字为4 给某个角色重新赋予 某个权限
     * @param en
     * @param dataScope
     * @return
     */
    public static int getMaxReplaceDataScope(String en, int dataScope){
        // 获取到最大的数据权限范围
        int dataScopeInteger = DataScope.DATA_SCOPE_SELF;
        for (RoleDTO r : UserUtil.getRoleList()){
            if (r.getEnname().equals(en)) {
                r.setDataScope(dataScope);
            }
            if (r.getDataScope() < dataScopeInteger){
                dataScopeInteger = r.getDataScope();
            }
        }
        return dataScopeInteger;
    }


    /**
     * 获取到最大的 数据权限 如果拥有某个角色就直接返回 该角色的权限范围或者自定义
     *   数值越小 数据权限最大 默认最小权限数字为4 给某个角色重新赋予 某个权限
     * @param en
     * @param dataScope 为 null 时表示 用本身角色权限
     * @return
     */
    public static int getMaxInDataScope(String en, Integer dataScope){
        // 获取到最大的数据权限范围
        int dataScopeInteger = DataScope.DATA_SCOPE_SELF;
        for (RoleDTO r : UserUtil.getRoleList()){
            if (r.getEnname().equals(en)) {
                if (dataScope == null) {
                    return r.getDataScope();
                }
               return dataScope;
            }
            if (r.getDataScope() < dataScopeInteger){
                dataScopeInteger = r.getDataScope();
            }
        }
        return dataScopeInteger;
    }

}
