package com.cloud.message.beans.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * @Author Aijm
 * @Description  发送短信
 * @Date 2019/9/10
 */
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMsgDTO implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信息类型：1短信2语音", required = true)
    private String msgType;
	@ApiModelProperty(value = "短信类型:1.用户注册验证码2.登录确认验证码3.")
	private String bizType;
	@ApiModelProperty(value = "手机号", required = true)
	private String mobile;
	@ApiModelProperty(value = "短信参数", required = false)
	private String params;
	@ApiModelProperty(value = "发送人", required = true)
	private String sender;
	@NotBlank(message = "模板code不能为空")
	@ApiModelProperty(value = "模板code", required = true)
	private String templateCode;


	@JsonIgnore
	private Map<String, String> map;
	/**
	 * dict 的字典类型
	 */
	public static final String PARAM_TYPE = "sms_send_params";


}
