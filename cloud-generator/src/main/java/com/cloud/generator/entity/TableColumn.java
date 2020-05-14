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
    private String name;

    /**
     * 字段备注
     */
    private String comment;

    /**
     * 字段类型
     */
    private String jdbcType;

}
