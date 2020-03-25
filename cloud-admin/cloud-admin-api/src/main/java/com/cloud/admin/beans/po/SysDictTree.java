package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项tree
 *
 * @author Aijm
 * @date 2019-09-05 20:00:25
 */
@Data
@TableName("sys_dict_tree")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "区域表")
public class SysDictTree extends TreeEntity<SysDictTree> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典类型code")
    private String typeCode;

    @ApiModelProperty(value = "编码；一般唯一")
    private String value;



}
