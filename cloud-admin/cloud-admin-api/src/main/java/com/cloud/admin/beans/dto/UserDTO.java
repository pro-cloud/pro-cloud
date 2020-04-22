package com.cloud.admin.beans.dto;


import com.cloud.admin.beans.po.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * <p>
 * 机构表
 * </p>
 *
 * @author Aijm
 * @since 2019-05-04
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="用户表")
public class UserDTO extends SysUser {


    /**
     * 所属部门
     */
    private OfficeDTO office;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 查询的条件
     */
    private RoleDTO role;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 拥有的所有角色
     */
    private List<RoleDTO> roleList;

    private List<Long> roleIdList;

    /**
     * 拥有的租户集合
     */
    private String tenantIds;

}
