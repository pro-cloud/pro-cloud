package com.cloud.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 生成代码数据源
 *
 * @author Aijm
 * @date 2020-05-14 23:00:48
 */
@Data
@TableName("gen_data_source")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生成代码数据源")
public class GenDataSource extends BaseEntity<GenDataSource> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "")
    private String url;

    @ApiModelProperty(value = "")
    private String username;

    @ApiModelProperty(value = "")
    private String password;


}
