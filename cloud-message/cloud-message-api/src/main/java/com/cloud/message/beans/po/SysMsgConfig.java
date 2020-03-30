package com.cloud.message.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信配置信息
 *
 * @author Aijm
 * @date 2019-09-10 13:40:14
 */
@Data
@TableName("sys_msg_config")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "短信配置信息")
public class SysMsgConfig extends BaseEntity<SysMsgConfig> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信平台地址")
    private String smsPlatUrl;

    @ApiModelProperty(value = "短信平台帐号")
    private String smsPlatAccount;

    @ApiModelProperty(value = "短信平台密码")
    @JsonIgnore
    private String smsPlatPassword;

    @ApiModelProperty(value = "发送短信签名")
    private String senderName;


}
