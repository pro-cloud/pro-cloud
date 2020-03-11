package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.cloud.common.entity.TreeEntity;
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
public class SysMenu extends TreeEntity<SysMenu> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "链接")
    @TableField(fill = FieldFill.UPDATE)
    private String href;

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
