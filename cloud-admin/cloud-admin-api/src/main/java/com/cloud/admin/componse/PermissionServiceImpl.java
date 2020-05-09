package com.cloud.admin.componse;

import com.cloud.admin.util.SysUserUtil;
import com.cloud.common.security.component.IPermissionService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Aijm
 * @date 2019/6/25
 * 接口权限判断工具
 */
@Slf4j
public class PermissionServiceImpl implements IPermissionService {


	/**
	 * 判断接口是否有权限
	 * 		内部用户为最高权限
	 * @param permission
	 * @return
	 */
	@Override
	public boolean hasPermission(String permission) {
		log.info("PermissionServiceImpl校验权限");
		return SysUserUtil.hasPermission(permission);
	}

	/**
	 * 判断当前用户是不是具有某个角色
	 * 内部用户为最高权限
	 *
	 * @param enname
	 * @return
	 */
	@Override
	public boolean hasRole(String enname) {
		return false;
	}


}
