package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.data.base.ProMapper;
import org.apache.ibatis.annotations.Param;


/**
 * 用户表
 *
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
public interface SysUserMapper extends ProMapper<SysUser> {

    /**
     * 分页查询
     * @param page
     * @param userDTO
     * @return
     */
    IPage<UserDTO> getSysUserPage(Page page, @Param("query") UserDTO userDTO);


}
