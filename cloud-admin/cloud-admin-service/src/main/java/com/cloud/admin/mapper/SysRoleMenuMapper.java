package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.po.SysRoleMenu;

/**
 * 角色-菜单
 *
 * @author Aijm
 * @date 2019-08-25 21:12:49
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 批量插入 角色和菜单的关联关系
     * @param roleDTO
     * @return
     */
    int insertRoleMenu(RoleDTO roleDTO);


    /**
     *  删除角色菜单管理关系
     * @param roleId
     * @return
     */
    int deleteRoleDTO(Long roleId);
}
