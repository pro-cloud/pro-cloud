package com.cloud.admin.datascope;

import lombok.experimental.UtilityClass;

/**
 * @Author Aijm
 * @Description 数据权限控制    数据范围
 *     1：所有数据；
 *     2：所在部门及以下数据；
 *     3：所在部门数据；
 *     4：仅本人数据；
 * @Date 2019/5/8
 */
@UtilityClass
public class DataScope {


	public static final int DATA_SCOPE_ALL = 1;
	public static final int DATA_SCOPE_OFFICE_AND_CHILD = 2;
	public static final int DATA_SCOPE_OFFICE = 3;
	public static final int DATA_SCOPE_SELF = 4;


	/**
	 * 个人权限关联的字段  一般只会用在sys_user 中
	 */
	public static final String FIELD_ID = "id";
	/**
	 * 个人权限关联的字段
	 */
	public static final String FIELD_CREATE_BY = "create_by";
}
