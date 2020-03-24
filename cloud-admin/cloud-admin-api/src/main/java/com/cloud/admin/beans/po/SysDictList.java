package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项list
 *
 * @author Aijm
 * @date 2019-09-05 19:52:37
 */
@Data
@TableName("sys_dict_list")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "字典项list")
public class SysDictList extends BaseEntity<SysDictList> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字段名称key")
    private String name;

    @ApiModelProperty(value = "值value")
    private String value;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "编码类型")
    private String typeCode;

    @ApiModelProperty(value = "排序（升序）")
    private Integer sort;


}
