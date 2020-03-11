package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUserRole;

/**
 * 用户-角色
 *
 * @author Aijm
 * @date 2019-08-25 21:08:47
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 删除用户角色关联数据
     * @param userId
     * @return
     */
    int deleteUserRole(Long userId);

    /**
     * 根据角色删除用户和角色关系
     * @param roleId
     * @return
     */
    int deleteUserRoleByRole(Long roleId);


    /**
     * 插入用户角色关联数据
     * @param userDTO
     * @return
     */
    int insertUserRole(UserDTO userDTO);


}
