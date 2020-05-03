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
import lombok.experimental.UtilityClass;


import java.util.List;

/**
 *  用户工具类  不使用service的原因是 防止初夏
 * @author Aijm
 * @since 2019/8/25
 */
@UtilityClass
public class UserUtil extends SecurityUtil {


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

}
