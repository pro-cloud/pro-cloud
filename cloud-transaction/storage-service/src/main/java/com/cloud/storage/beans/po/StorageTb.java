package com.cloud.storage.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author Aijm
 * @date 2020-03-15 18:17:15
 */
@Data
@TableName("storage_tb")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class StorageTb extends BaseEntity<StorageTb> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String commodityCode;

    @ApiModelProperty(value = "")
    private Integer count;


}
