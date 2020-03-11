package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 角色表
 *
 * @author Aijm
 * @date 2019-08-25 20:57:31
 */
@Data
@TableName("sys_role")
@Accessors(chain = true)
@ApiModel(description = "角色表")
public class SysRole extends BaseEntity<SysRole> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "英文名称")
    private String enname;

    @ApiModelProperty(value = "角色类型")
    private Integer roleType;

    @ApiModelProperty(value = "数据范围")
    private Integer dataScope;



}
