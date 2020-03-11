package com.cloud.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.data.base.IProService;


/**
 * 用户表
 *
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
public interface SysUserService extends IProService<SysUser> {

    /**
     * 分页查询
     * @param page
     * @param userDTO
     * @return
     */
    IPage<UserDTO> getSysUserPage(Page page, UserDTO userDTO);


    /**
     * 获取用户详细信息
     * @param userDTO
     * @return
     */
    boolean saveUserDTO(UserDTO userDTO);


    /**
     * 获取用户详细信息
     * @param userDTO
     * @return
     */
    boolean updateUserDTO(UserDTO userDTO);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean removeUserDTO(Long id);

}
