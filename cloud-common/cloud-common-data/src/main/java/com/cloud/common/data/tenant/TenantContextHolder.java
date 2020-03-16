package com.cloud.common.data.tenant;

import lombok.experimental.UtilityClass;

/**
 * 租户工具类
 * @author Aijm
 * @since 2020/1/14
 */
@UtilityClass
public class TenantContextHolder {

	private final ThreadLocal<String> THREAD_LOCAL_TENANT = new ThreadLocal<>();


	/**
	 * 设置租户的id
	 * @param tenantIds
	 */
	public void setTenantId(String tenantIds) {
		THREAD_LOCAL_TENANT.set(tenantIds);
	}

	/**
	 * 租户的id
	 * @return
	 */
	public String getTenantIds() {
		return THREAD_LOCAL_TENANT.get();
	}

	public void clearTenantIds() {
		THREAD_LOCAL_TENANT.remove();
	}
}
