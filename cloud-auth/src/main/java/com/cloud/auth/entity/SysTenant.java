package com.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.TenantEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 租户管理
 *
 * @author Aijm
 * @date 2020-05-25 13:32:23
 */
@Data
@TableName("sys_tenant")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "租户管理")
public class SysTenant extends TenantEntity<SysTenant> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户名称(公司名称)")
    private String companyName;

    @ApiModelProperty(value = "租户管理登录用户名(为超级管理员)")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "qq号")
    private String qqNum;

    @ApiModelProperty(value = "微信号")
    private String wxNum;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime begDate;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "状态0 启用 1 禁用")
    private Integer status;


}
