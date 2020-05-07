package com.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.cloud.admin.mapper.SysRoleMenuMapper;
import com.cloud.admin.mapper.SysUserRoleMapper;
import com.cloud.common.cache.util.CacheUtil;
import com.cloud.common.data.base.BaseService;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.po.SysRole;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.mapper.SysRoleMapper;
import com.cloud.admin.service.SysRoleService;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.constants.CacheScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色表
 *
 * @author Aijm
 * @date 2019-08-25 20:57:31
 */
@Service
public class SysRoleServiceImpl extends BaseService<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;



    @Override
    public RoleDTO get(Long id) {
        return sysRoleMapper.get(id) ;
    }

    @Override
    @CacheClear(scope = CacheScope.USER_ROLE, key = "'*'", pattern = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByRole(RoleDTO roleDTO) {
        chealRoleMenu(roleDTO);
        return super.updateById(roleDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleDTO(RoleDTO roleDTO) {
        // 必须先报错用户信息为了获取到用户的id
        boolean save = super.save(roleDTO);
        chealRoleMenu(roleDTO);
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRoleById(Long id) {
        // 删除角色和菜单的关联关系
        sysRoleMenuMapper.deleteRoleDTO(id);
        // 删除用户和角色的关联关系
        sysUserRoleMapper.deleteUserRole(id);
        // 手动清理缓存
        CacheUtil.removeCacheName(CacheScope.USER_MENU.getCacheName());
        CacheUtil.removeCacheName(CacheScope.USER_ROLE.getCacheName());
        return super.removeById(id);
    }

    @Override
    @Cache(scope = CacheScope.USER_ROLE, key = "#sysUser.id")
    public List<RoleDTO> findList(SysUser sysUser) {
        return sysRoleMapper.findList(sysUser);
    }

    /**
     * 处理角色和菜单的关系
     * @param roleDTO
     */
    private void chealRoleMenu(RoleDTO roleDTO) {
        // 先删除关联关系,再批量插入
        sysRoleMenuMapper.deleteRoleDTO(roleDTO.getId());
        if (CollUtil.isNotEmpty(roleDTO.getMenuList())) {
            sysRoleMenuMapper.insertRoleMenu(roleDTO);
        }
    }
}
