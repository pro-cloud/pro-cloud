package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 区域表
 *
 * @author Aijm
 * @date 2019-08-25 21:54:16
 */
@Data
@TableName("sys_area")
@Accessors(chain = true)
@ApiModel(description = "区域表")
public class SysArea extends TreeEntity<SysArea> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "区域编码")
    private String code;

    @ApiModelProperty(value = "区域类型")
    private String type;


}
