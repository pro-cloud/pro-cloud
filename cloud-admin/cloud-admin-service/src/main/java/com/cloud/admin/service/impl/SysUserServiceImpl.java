package com.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.datascope.DataScope;
import com.cloud.admin.datascope.DataScopeFilters;
import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.admin.mapper.SysUserRoleMapper;
import com.cloud.admin.service.SysUserService;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.annotation.CacheConf;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.data.base.BaseService;
import com.cloud.common.util.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;


/**
 * 用户表
 *    特别注意的是 不能调用BaseService中其他更新/删除数据的代码
 *                  如要调用则需要清空对应缓存
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
@Service
@CacheConf(scope = CacheScope.USER_USER)
public class SysUserServiceImpl extends BaseService<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public IPage<UserDTO> getSysUserPage(Page page, UserDTO userDTO) {
        DataScopeFilters.dataScopeEntity(userDTO,"o", "u", DataScope.FIELD_ID);
        userDTO.setHasAdmin(UserUtil.hasAdmin());
        return sysUserMapper.getSysUserPage(page, userDTO);
    }


    @Override
    @Cache(key = "#id")
    public SysUser getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserDTO(UserDTO userDTO) {
        // 必须先报错用户信息为了获取到用户的id
        boolean save = save(userDTO);
        chealRole(userDTO);
        return save;
    }

    /**
     * 校验是否可以修改或者注册
     *
     * @param userDTO
     * @return
     */
    @Override
    public boolean getCheckUserDTO(UserDTO userDTO) {
        Integer count = sysUserMapper.selectCount(Wrappers.<SysUser>query()
                .lambda()
                .eq(SysUser::getLoginName, userDTO.getLoginName())
                .or(StrUtils.isNotBlank(userDTO.getEmail()))
                .eq(SysUser::getEmail, userDTO.getEmail())
                .or(StrUtils.isNotBlank(userDTO.getMobile()))
                .eq(SysUser::getMobile, userDTO.getMobile())
        );
        return count > 0? true : false;
    }


    /**
     * 清空 用户和角色对应、 用户和菜单缓存和用户缓存
     * @param userDTO
     * @return
     */
    @Override
    @CacheClear(scope = CacheScope.USER, key = "'*:'+"+"#userDTO.id", pattern = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserDTO(UserDTO userDTO) {
        // 更新角色信息
        chealRole(userDTO);
        return updateById(userDTO);
    }

    /**
     * 清空 用户和角色对应、 用户和菜单缓存和用户缓存
     * @param id
     * @return
     */
    @Override
    @CacheClear(scope = CacheScope.USER, key = "'*:'+"+"#id", pattern = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean removeUserDTO(Long id) {
        //  需要删除和角色关联关系
        sysUserRoleMapper.deleteUserRole(id);
        return retBool(sysUserMapper.deleteById(id));
    }


    /**
     * 处理 角色 先删除关联关系 然后重建 关联关系
     * @param userDTO
     */
    private void chealRole(UserDTO userDTO) {
        // 更新用户与角色关联
        sysUserRoleMapper.deleteUserRole(userDTO.getId());
        if (CollUtil.isNotEmpty(userDTO.getRoleList())){
            sysUserRoleMapper.insertUserRole(userDTO);
        }
    }
}
