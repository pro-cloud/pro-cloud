package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
@Data
@TableName("sys_user")
@Accessors(chain = true)
@ApiModel(description = "用户表")
public class SysUser extends BaseEntity<SysUser> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "归属部门")
    private Long officeId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "错误的邮箱格式")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "最后登陆IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登陆时间")
    private LocalDateTime loginDate;

    @ApiModelProperty(value = "是否可登录")
    private String loginFlag;

    @ApiModelProperty(value = "微信openid")
    private String wxOpenid;

    @ApiModelProperty(value = "QQ openid")
    private String qqOpenid;

}
