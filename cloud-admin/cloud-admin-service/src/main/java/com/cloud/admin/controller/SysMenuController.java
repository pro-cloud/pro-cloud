package com.cloud.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.dto.MenuDTO;
import com.cloud.admin.beans.po.SysMenu;
import com.cloud.admin.service.SysMenuService;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.util.CacheUtil;
import com.cloud.common.data.base.BaseController;
import com.cloud.common.data.util.ObjUtil;
import com.cloud.common.data.util.TreeUtil;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;


/**
 * <p>
 * 菜单表 只允许超级管理员操作
 *  不是超级管理员添加菜单不会显示
 * </p>
 *
 * @author Aijm
 * @since 2019-05-13
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 查询 简单树
     * @return
     */
    @GetMapping("/listALL")
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_view')")
    public Result getSysMenuAll() {
        return Result.success(UserUtil.getMenuList());
    }

    /**
     * 查询 标准树
     * @return
     */
    @GetMapping("/listTree")
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_view')")
    public Result getSysMenuAllTree() {
        List<SysMenu> menus = UserUtil.getMenuList();
        // 剔除隐藏的菜单
        Iterator<SysMenu> it = menus.iterator();
        while (it.hasNext()) {
            SysMenu menu = it.next();
            if (MenuDTO.HAS_HIDE.equals(menu.getHasShow())) {
                it.remove();
            }
        }
        return Result.success(TreeUtil.buildTree(menus, TreeUtil.ROOT_PID));
    }
    /**
     * 通过id查询菜单表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysMenuService.getById(id));
    }

    /**
     * 新增菜单表
     * @param sysMenu 菜单表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_add')")
    public Result save(@RequestBody @Valid SysMenu sysMenu) {
        // 手动清空菜单缓存
        CacheUtil.remove(CacheScope.USER_MENU.getCacheName(), UserUtil.getUserId().toString());
        return Result.success(sysMenuService.save(sysMenu));
    }

    /**
     * 修改菜单表
     * @param sysMenu 菜单表
     * @return R
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_edit')")
    public Result updateById(@RequestBody @Valid SysMenu sysMenu) {
        // 手动清空菜单缓存
        CacheUtil.removeCacheName(CacheScope.USER_MENU.getCacheName());
        return Result.success(sysMenuService.updateById(sysMenu));
    }

    /**
     * 通过id删除菜单表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_del')")
    public Result removeById(@PathVariable Long id) {
        // 判断是否还有子节点
        List<SysMenu> list = sysMenuService.list(Wrappers.<SysMenu>query().lambda()
                .like(SysMenu::getParentIds, id + ","));
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error(ResultEnum.CRUD_DELETE_NOT);
        }
        // 注解清空缓存
        return Result.success(sysMenuService.removeById(id));
    }


    /**
     * isShowHide是否显示隐藏菜单  弹出树
     * @param extId 表示当前节点的id
     * @param isShowHide 0 表示不显示隐藏
     * @return
     */
    @PreAuthorize("@pms.hasPermission('admin_sysmenu_view')")
    @GetMapping(value = "treeData")
    public Result treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) Integer isShowHide) {
        List<SysMenu> list = UserUtil.getMenuList();
        SysMenu menuP = new SysMenu();
        menuP.setHasShow(MenuDTO.HAS_SHOW);
        menuP.setName("我的菜单").setParentId(TreeUtil.ROOT_P_ID).setParentIds("").setId(0L);
        list.add(menuP);
        // 存储处理后的数据
        List<SysMenu> mapList = Lists.newArrayList();
        for (SysMenu sysMenu : list) {
            boolean hasExtId = extId != null && !extId.equals(sysMenu.getId().toString()) && sysMenu.getParentIds().indexOf("," + extId + ",") == -1;
            if (StrUtil.isBlank(extId) || hasExtId ){
                if(isShowHide != null && MenuDTO.HAS_HIDE.equals(isShowHide) && MenuDTO.HAS_HIDE.equals(sysMenu.getHasShow())){
                    continue;
                }
                mapList.add(sysMenu);
            }
        }
        return Result.success(TreeUtil.buildTree(mapList, TreeUtil.ROOT_P_ID));
    }
}

