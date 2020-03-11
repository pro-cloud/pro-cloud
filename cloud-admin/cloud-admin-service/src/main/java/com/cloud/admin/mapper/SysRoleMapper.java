package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.po.SysRole;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.data.base.ProMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表
 *
 * @author Aijm
 * @date 2019-08-25 20:57:31
 */
public interface SysRoleMapper extends ProMapper<SysRole> {


    /**
     * 根据id 获取 拥有的菜单
     * @param roleId
     * @return
     */
    RoleDTO get(Long roleId);


    /**
     * 根据用户 查询拥有的角色信息
     * @param sysUser
     * @return
     */
    List<RoleDTO> findList(SysUser sysUser);



}
