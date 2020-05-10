package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.cloud.common.entity.TenantTreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Aijm
 * @since 2019-05-13
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class SysMenu extends TenantTreeEntity<SysMenu> {

    private static final long serialVersionUID = -481808875271508064L;

    @ApiModelProperty(value = "菜单类型")
    private String type;

    @ApiModelProperty(value = "目标")
    private String target;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否在菜单中显示")
    @TableField("is_show")
    private Integer hasShow;

    @ApiModelProperty(value = "权限标识")
    private String permission;

}
