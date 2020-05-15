package com.cloud.generator.config;


import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import com.cloud.generator.util.DataSourceConstants;
import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 动态数据源切换配置
 * @author Aijm
 * @since 2019/6/16
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicDataSourceConfig implements TransactionManagementConfigurer {
	private final Map<Object, Object> dataSourceMap = Maps.newHashMap();
	private final DataSourceProperties dataSourceProperties;

	@Bean("dynamicDataSource")
	public DynamicDataSource dataSource() {
		DynamicDataSource ds = new DynamicDataSource();
		HikariDataSource cads = new HikariDataSource();
		cads.setJdbcUrl(dataSourceProperties.getUrl());
		cads.setDriverClassName(dataSourceProperties.getDriverClassName());
		cads.setUsername(dataSourceProperties.getUsername());
		cads.setPassword(dataSourceProperties.getPassword());
		ds.setDefaultTargetDataSource(cads);
		dataSourceMap.put("0", cads);
		ds.setTargetDataSources(dataSourceMap);
		return ds;
	}

	/**
	 * 组装默认配置的数据源，查询数据库配置
	 */
	@PostConstruct
	public void init() throws SQLException {
		DriverManagerDataSource dds = new DriverManagerDataSource();
		dds.setUrl(dataSourceProperties.getUrl());
		dds.setDriverClassName(dataSourceProperties.getDriverClassName());
		dds.setUsername(dataSourceProperties.getUsername());
		dds.setPassword(dataSourceProperties.getPassword());

		List<Entity> query = DbUtil.use(dds).query(DataSourceConstants.QUERY_DATA_SOURCE, null);
		log.info("初始化动态数据源");
		Optional.of(query).ifPresent(list -> list.forEach(db -> {
			log.info("数据源:{}", db.get(DataSourceConstants.DS_NAME));
			HikariDataSource ds = new HikariDataSource();
			ds.setJdbcUrl(String.valueOf(db.get(DataSourceConstants.DS_JDBC_URL)));
			ds.setDriverClassName(dataSourceProperties.getDriverClassName());
			ds.setUsername((String) db.get(DataSourceConstants.DS_USER_NAME));
			String decPwd = (String) db.get(DataSourceConstants.DS_USER_PWD);
			ds.setPassword(decPwd);
			dataSourceMap.put(db.get(DataSourceConstants.DS_ROUTE_KEY).toString(), ds);
		}));
	}

	/**
	 * 重新加载数据源配置
	 */
	public Boolean reload() throws SQLException {
		init();
		DynamicDataSource dataSource = dataSource();
		dataSource.setTargetDataSources(dataSourceMap);
		dataSource.afterPropertiesSet();
		return Boolean.FALSE;
	}


	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

}