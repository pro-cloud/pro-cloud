package com.cloud.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * tree的基础
 * @author Aijm
 * @since 2019/5/4
 */
@Data
@Accessors(chain = true)
public class TreeEntity<T> extends BaseEntity<T> {


	@ApiModelProperty(value = "父级编号")
    protected Long parentId;

    @ApiModelProperty(value = "所有父级编号")
    protected String parentIds;

    @ApiModelProperty(value = "名称")
    protected String name;

    @ApiModelProperty(value = "排序")
    protected Integer sort;
    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    @TableField(exist = false)
    protected List<T> children = Lists.newArrayList();


}
