package com.cloud.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 生成代码
 *
 * @author Aijm
 * @date 2020-05-14 22:44:46
 */
@Data
@TableName("gen_scheme")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生成代码")
public class GenScheme extends BaseEntity<GenScheme> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据源id")
    private Long sourceId;

    @ApiModelProperty(value = "作者名")
    private String author;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "生成包路径")
    private String packageName;

    @ApiModelProperty(value = "生成模块名")
    private String moduleName;

    @ApiModelProperty(value = "生成功能名 也为实体名")
    private String className;


}
