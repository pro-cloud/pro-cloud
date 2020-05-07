package com.cloud.admin.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 机构表
 *
 * @author Aijm
 * @date 2019-08-25 20:45:42
 */
@Data
@TableName("sys_office")
@Accessors(chain = true)
@ApiModel(description = "机构表")
public class SysOffice extends TreeEntity<SysOffice> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "主负责人")
    private String primaryPerson;

    @ApiModelProperty(value = "状态")
    private Integer status;



}
