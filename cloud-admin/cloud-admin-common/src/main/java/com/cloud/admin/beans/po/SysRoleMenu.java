package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单
 *
 * @author Aijm
 * @date 2019-08-25 21:12:49
 */
@Data
@TableName("sys_role_menu")
@ApiModel(description = "角色-菜单")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编号")
    private Long roleId;

    @ApiModelProperty(value = "菜单编号")
    private Long menuId;


}
