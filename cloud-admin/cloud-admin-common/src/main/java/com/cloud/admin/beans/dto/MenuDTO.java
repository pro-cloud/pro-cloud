package com.cloud.admin.beans.dto;

import com.cloud.admin.beans.po.SysMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Aijm
 * @Description 菜单详细信息
 * @Date 2019/8/13
 */
@Data
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="菜单")
public class MenuDTO extends SysMenu {

    /**
     * 菜单类型  1：表示显示 0： 表示隐藏菜单
     */
    public static final Integer HAS_SHOW = 1;

    public static final Integer HAS_HIDE = 0;


}
