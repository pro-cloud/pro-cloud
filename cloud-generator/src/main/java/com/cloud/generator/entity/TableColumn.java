package com.cloud.generator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字段名称
 * @author Aijm
 * @since 2020/5/14
 */
@Data
public class TableColumn implements Serializable {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段备注
     */
    private String comments;

    /**
     * 字段类型
     */
    private String jdbcType;

    /**
     * 数据类型
     */
    private String dataType;

}
