package com.cloud.generator.dto;

import com.cloud.generator.entity.TableColumn;
import lombok.Data;

/**
 * 表字段
 * @author Aijm
 * @since 2020/5/16
 */
@Data
public class TableColumnDTO extends TableColumn {

    /**
     * 字段类型
     */
    private String attrType;

    /**
     * 实体名称
     */
    private String lowerAttrName;
}
