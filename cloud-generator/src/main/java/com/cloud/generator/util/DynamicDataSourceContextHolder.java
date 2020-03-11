package com.cloud.generator.util;

import lombok.experimental.UtilityClass;

/**
 * 保存上线文中数据库路由
 * @author Aijm
 * @since 2019/6/16
 */
@UtilityClass
public class DynamicDataSourceContextHolder {

	private final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	public void setDataSourceType(String dataSourceType) {
		CONTEXT_HOLDER.set(dataSourceType);
	}

	public String getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}

	public void clearDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
}