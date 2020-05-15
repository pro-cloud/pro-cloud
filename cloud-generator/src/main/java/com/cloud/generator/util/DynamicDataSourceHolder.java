package com.cloud.generator.util;

import lombok.experimental.UtilityClass;

/**
 * 保存上线文中数据库路由
 * @author Aijm
 * @since 2019/6/16
 */
@UtilityClass
public class DynamicDataSourceHolder {

	private final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<>();

	public void setDataSourceType(String sourceId) {
		DATA_SOURCE.set(sourceId);
	}

	public String getDataSourceType() {
		return DATA_SOURCE.get();
	}

	public void clearDataSourceType() {
		DATA_SOURCE.remove();
	}
}