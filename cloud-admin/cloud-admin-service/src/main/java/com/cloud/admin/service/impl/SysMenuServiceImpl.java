package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.dto.MenuDTO;
import com.cloud.admin.beans.po.SysMenu;
import com.cloud.admin.mapper.SysMenuMapper;
import com.cloud.admin.service.SysMenuService;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.data.base.TreeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Aijm
 * @since 2019-05-13
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends TreeService<SysMenuMapper, SysMenu> implements SysMenuService {


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @CacheClear(scope = CacheScope.USER_MENU, key = "'*'", pattern = true)
    public boolean updateById(SysMenu entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheClear(scope = CacheScope.USER_MENU, key = "'*'", pattern = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Cache(scope = CacheScope.USER_MENU, key = "#userId")
    public List<SysMenu> findByUserId(Long userId) {
        if (UserUtil.hasAdmin(userId)) {
            return sysMenuMapper.selectList(Wrappers.<SysMenu>query()
                    .lambda().orderByAsc(SysMenu::getSort) );
        }
        return sysMenuMapper.findByUserId(userId);
    }

}
