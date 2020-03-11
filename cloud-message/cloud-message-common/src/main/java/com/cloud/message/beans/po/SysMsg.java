package com.cloud.message.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信发送消息日志
 *
 * @author Aijm
 * @date 2019-09-10 13:45:16
 */
@Data
@TableName("sys_msg")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "短信发送消息日志")
public class SysMsg extends BaseEntity<SysMsg> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发短信请求id")
    private String requestId;

    @ApiModelProperty(value = "短信类型:1.用户注册验证码2.登录确认验证码")
    private String type;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发送状态 0 失败1 成功")
    private Integer sendState;


}
