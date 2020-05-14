package com.cloud.generator.config;


import com.cloud.generator.util.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源类
 * @author Aijm
 * @since 2019/6/16
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 指定路由Key
	 *
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceType();
	}
}