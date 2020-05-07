package com.cloud.admin.compoent;

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
	 * 判断接口是否有权限
	 * 		内部用户为最高权限
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(String permission) {
		return UserUtil.hasPermission(permission);
	}


	/**
	 * 判断当前用户是不是具有某个角色
	 * 内部用户为最高权限
	 * @param enname
	 * @return
	 */
	public boolean hasRole(String enname) {
		return UserUtil.hasRole(enname);
	}
}
