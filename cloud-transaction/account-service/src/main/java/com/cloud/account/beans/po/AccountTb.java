package com.cloud.account.beans.po;

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
 * @date 2020-03-15 18:16:02
 */
@Data
@TableName("account_tb")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class AccountTb extends BaseEntity<AccountTb> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String userId;

    @ApiModelProperty(value = "")
    private Integer money;


}
