package com.cloud.generator.util;

import lombok.experimental.UtilityClass;

/**
 * 数据源相关常量
 * @author Aijm
 * @since 2019/6/16
 */
@UtilityClass
public class DataSourceConstants {
	/**
	 * 查询数据源的SQL
	 */
	public static final String QUERY_DS_SQL = "select * from gen_data_source_conf where del_flag = 0";

	/**
	 * 动态路由KEY
	 */
	public static final String DS_ROUTE_KEY = "id";

	/**
	 * 数据源名称
	 */
	public static final String DS_NAME = "name";

	/**
	 * jdbcurl
	 */
	public static final String DS_JDBC_URL = "url";

	/**
	 * 用户名
	 */
	public static final String DS_USER_NAME = "username";

	/**
	 * 密码
	 */
	public static final String DS_USER_PWD = "password";

}
