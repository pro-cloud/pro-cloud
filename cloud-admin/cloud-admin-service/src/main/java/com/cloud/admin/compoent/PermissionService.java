package com.cloud.admin.compoent;

import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.po.SysMenu;
import com.cloud.admin.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author Aijm
 * @date 2019/6/25
 * 接口权限判断工具
 */
@Slf4j
@Component("pms")
public class PermissionService {


	/**
	 * 判断接口是否有权限 内部用户为最高权限
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(String permission) {
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
	 * 判断当前用户是不是具有某个角色
	 * 内部用户为最高权限
	 * @param enname
	 * @return
	 */
	public boolean hasRole(String enname) {
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
