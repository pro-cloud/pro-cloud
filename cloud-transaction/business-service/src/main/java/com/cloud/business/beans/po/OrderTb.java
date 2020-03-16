package com.cloud.business.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:34:59
 */
@Data
@TableName("order_tb")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class OrderTb extends BaseEntity<OrderTb> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String userId;

    @ApiModelProperty(value = "")
    private String commodityCode;

    @ApiModelProperty(value = "")
    private Integer count;

    @ApiModelProperty(value = "")
    private Integer money;


}
