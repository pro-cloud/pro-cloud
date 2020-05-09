package com.cloud.common.security.component;

/**
 * @Author Aijm
 * @Description 权限接口
 * @Date 2020/5/9
 */
public interface IPermissionService {


    /**
     * 判断接口是否有权限
     * 		内部用户为最高权限
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission);


    /**
     * 判断当前用户是不是具有某个角色
     * 内部用户为最高权限
     * @param enname
     * @return
     */
    public boolean hasRole(String enname);
}
