package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表
 *
 * @author Aijm
 * @date 2019-09-05 16:53:21
 */
@Data
@TableName("sys_dict")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "字典表")
public class SysDict extends BaseEntity<SysDict> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型字典/树")
    private String name;

    @ApiModelProperty(value = "类型字典/树")
    private Integer type;

    @ApiModelProperty(value = "类型编码")
    private String typeCode;

    @ApiModelProperty(value = "是否是系统级别数据 0 普通 1 系统")
    private Integer system;


}
