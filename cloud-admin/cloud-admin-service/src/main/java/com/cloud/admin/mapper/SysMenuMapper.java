package com.cloud.admin.mapper;

import com.cloud.admin.beans.po.SysMenu;
import com.cloud.common.data.base.TreeMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Aijm
 * @since 2019-05-13
 */
public interface SysMenuMapper extends TreeMapper<SysMenu> {


    /**
     *  根据用户id获取到用户的菜单
     * @param userId
     * @return
     */
    List<SysMenu> findByUserId(Long userId);

}
