package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 用户-角色
 *
 * @author Aijm
 * @date 2019-08-25 21:08:47
 */
@Data
@TableName("sys_user_role")
@Accessors(chain = true)
@ApiModel(description = "用户-角色")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "角色编号")
    private Long roleId;


}
