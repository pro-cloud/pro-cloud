package com.cloud.common.data.tenant;

import lombok.experimental.UtilityClass;

/**
 * 租户工具类
 * @author Aijm
 * @since 2020/1/14
 */
@UtilityClass
public class TenantContextHolder {

	private final ThreadLocal<Integer> THREAD_LOCAL_TENANT = new ThreadLocal<>();


	/**
	 * 设置租户的id
	 * @param tenantId
	 */
	public void setTenantId(Integer tenantId) {
		THREAD_LOCAL_TENANT.set(tenantId);
	}

	/**
	 * 租户的id
	 * @return
	 */
	public Integer getTenantId() {
		return THREAD_LOCAL_TENANT.get();
	}

	public void clearTenantId() {
		THREAD_LOCAL_TENANT.remove();
	}
}
