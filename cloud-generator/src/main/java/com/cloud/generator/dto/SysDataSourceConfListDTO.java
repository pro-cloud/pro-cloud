package com.cloud.generator.dto;

import com.cloud.generator.entity.SysDataSourceConf;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;

/**
 *数据源配置集合
 * @author Aijm
 * @since 2019/6/16
 */
@Data
@Accessors(chain = true)
public class SysDataSourceConfListDTO implements Serializable {

    @ApiModelProperty(value = "数据源配置集合")
    private List<SysDataSourceConf> sysDataSourceConfs = Lists.newArrayList();
}
