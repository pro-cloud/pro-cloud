package com.cloud.generator.entity;

import lombok.Data;

/**
 * 生成配置
 * @author Aijm
 * @since 2019/6/16
 */
@Data
public class GenConfig {
	/**
	 * 数据源ID
	 */
	private String id;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表备注
	 */
	private String comments;
}
