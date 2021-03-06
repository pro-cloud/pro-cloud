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
import com.cloud.common.security.util.SecurityUtil;
import com.cloud.common.util.var.StaticVar;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

/**
 *  用户工具类  不使用service的原因是 防止初夏
 * @author Aijm
 * @since 2019/8/25
 */
@UtilityClass
@Slf4j
public class UserUtil extends SecurityUtil {


    private static SysMenuService sysMenuService = SpringUtil.getBean(SysMenuService.class);

    private static SysRoleService sysRoleService = SpringUtil.getBean(SysRoleService.class);

    private static SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);


    //////////////////  菜单相关  /////////////////////////
    /**
     * 获取某用户id 菜单信息
     * @param userId
     * @return
     */
    public static List<SysMenu> getMenuList(Long userId){
        return sysMenuService.findByUserId(userId);
    }

    /**
     * 获取当前用户授权菜单
     * @return
     */
    public static List<SysMenu> getMenuList(){
        Long userId = getUserId();
        return getMenuList(userId);
    }


    ///////////////////////用户相关 //////////////////////////////////

    /**
     * 判断该用户是不是超级管理员 并给admin赋值
     * @param id 用户id
     * @return
     */
    public static boolean hasAdmin(Long id){
        SysUser user = getUser(id);
        return StaticVar.DEFAULT_USERTYPE_ADMIN.equals(user.getUserType());
    }

    /**
     * 获取某个 用户不包括角色 和部门信息
     * @param userId
     * @return
     */
    public static SysUser getUser(Long userId){
        return sysUserService.getById(userId);
    }


    /**
     * 获取当前用户详细信息 不包括角色 和部门信息
     * @return
     */
    public static SysUser getUser(){
        Long userId = getUserId();
        return getUser(userId);
    }

    /**
     * 获取到某用户详细信息
     * @param userId
     * @return
     */
    public static UserDTO getUserDTO(Long userId){
        UserDTO userDTO = new UserDTO();
        // 获取用户信息
        SysUser sysUser = getUser(userId);
        BeanUtil.copyProperties(sysUser, userDTO);
        // 获取用户部门信息
        SysOffice userOffice = OfficeUtil.getOffice(sysUser.getOfficeId());
        OfficeDTO officeDTO = new OfficeDTO();
        BeanUtil.copyProperties(userOffice, officeDTO);
        userDTO.setOffice(officeDTO);
        // 获取角色信息
        userDTO.setRoleList(getRoleList(userId));
        return userDTO;
    }


    /**
     * 获取用户详细 信息 包含 部门信息和角色信息
     *  想获取权限/菜单 需调用
     * @return
     */
    public static UserDTO getUserDTO(){
        Long userId = getUserId();
        return getUserDTO(userId);
    }



    /////////////////////////  角色相关 ////////////////////////////

    /**
     * 获取userId用户的角色信息
     * @return
     */
    public static List<RoleDTO> getRoleList(Long userId){
        return  sysRoleService.findList(getUser(userId));
    }

    /**
     * 获取当前用户的角色信息
     * @return
     */
    public static List<RoleDTO> getRoleList(){
        return  getRoleList(getUserId());
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


    /**
     * 判断接口是否有权限 内部用户为最高权限
     * @param permission
     * @return
     */
    public static boolean hasPermission(String permission) {
        // 判断是不是内部调用或者最高权限
        if (UserUtil.hasAdmin()) {
            return true;
        }
        for (SysMenu sysMenu : UserUtil.getMenuList()) {
            if (permission.equals(sysMenu.getPermission())) {
                return true;
            }
        }
        log.info("该用户没有:{}权限", permission);
        return false;
    }

    /**
     * 判断接口是否有权限 内部用户为最高权限
     * @param permiss 多个权限以逗号分隔开
     * @return
     */
    public static boolean hasPermiss(String permiss) {
        String[] pms = permiss.split(",");
        for (String pm : pms) {
            if(hasPermission(pm)) {
                return true;
            }
        }
        log.info("该用户没有:{}权限", permiss);
        return false;
    }

    /**
     * 判断当前用户是不是具有某个角色
     * 内部用户为最高权限
     * @param enname
     * @return
     */
    public static boolean hasRole(String enname) {
        // 判断是不是内部调用或者最高权限
        if (UserUtil.hasAdmin()) {
            return true;
        }
        for (RoleDTO roleDTO : UserUtil.getRoleList()) {
            if (roleDTO.getEnname().equals(enname)) {
                return true;
            }
        }
        log.info("该用户没有:{}角色", enname);
        return false;
    }
}
