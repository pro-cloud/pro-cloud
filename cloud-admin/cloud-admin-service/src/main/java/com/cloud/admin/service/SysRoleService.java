package com.cloud.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.IProService;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.po.SysRole;
import com.cloud.admin.beans.po.SysUser;

import java.util.List;

/**
 * 角色表
 *
 * @author Aijm
 * @date 2019-08-25 20:57:31
 */
public interface SysRoleService extends IProService<SysRole> {

    /**
     * 根据用户 查询拥有的角色信息
     * @param sysUser
     * @return
     */
    List<RoleDTO> findList(SysUser sysUser);

    /**
     * 更新角色信息
     * @param roleDTO
     * @return
     */
    boolean updateByRole(RoleDTO roleDTO);

    /**
     * 保存角色信息
     * @param roleDTO
     * @return
     */
    boolean saveRoleDTO(RoleDTO roleDTO);

    /**
     * 根据角色id 删除角色
     * @param id
     * @return
     */
    boolean removeRoleById(Long id);

}
