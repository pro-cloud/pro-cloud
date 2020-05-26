package com.cloud.admin.controller;

import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.util.RedisUtil;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.po.SysTenant;
import com.cloud.admin.service.SysTenantService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 租户管理
 *
 * @author Aijm
 * @date 2020-05-25 13:32:23
 */
@RestController
@RequestMapping("/systenant" )
@Api(value = "systenant", tags = "systenant管理")
public class SysTenantController {

    @Autowired
    private SysTenantService sysTenantService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysTenant 租户管理
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin_systenant_view')")
    public Result getSysTenantPage(Page page, SysTenant sysTenant) {
        return Result.success(sysTenantService.page(page, Wrappers.query(sysTenant)));
    }


    /**
     * 通过id查询租户管理
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_systenant_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysTenantService.getById(id));
    }

    /**
     * 新增租户管理
     * @param sysTenant 租户管理
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_systenant_add')")
    public Result save(@RequestBody @Valid SysTenant sysTenant) {
        sysTenant.setTenantId(sysTenantService.getNextTenantId());
        return Result.success(sysTenantService.save(sysTenant));
    }

    /**
     * 修改租户管理
     * @param sysTenant 租户管理
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_systenant_edit')")
    public Result updateById(@RequestBody @Valid SysTenant sysTenant) {
        SysTenant byId = sysTenantService.getById(sysTenant.getId());
        RedisUtil.remove(CacheScope.TENTANT_KEY.getCacheName(), String.valueOf(byId.getTenantId()));
        sysTenant.setTenantId(null);
        return Result.success(sysTenantService.updateById(sysTenant));
    }

    /**
     * 通过id删除租户管理
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_systenant_del')")
    public Result removeById(@PathVariable Long id) {
        SysTenant byId = sysTenantService.getById(id);
        RedisUtil.remove(CacheScope.TENTANT_KEY.getCacheName(), String.valueOf(byId.getTenantId()));
        return Result.success(sysTenantService.removeById(id));
    }

}
