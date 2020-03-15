package com.cloud.admin.controller;

import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.po.SysArea;
import com.cloud.admin.service.SysAreaService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 区域表
 *
 * @author Aijm
 * @date 2019-08-25 21:54:16
 */
@RestController
@RequestMapping("/area" )
@Api(value = "area", tags = "sysarea管理")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysArea 区域表
     * @return
     */
    @GetMapping("/page")
    public Result getSysAreaPage(Page page, SysArea sysArea) {
        return Result.success(sysAreaService.page(page, Wrappers.query(sysArea)));
    }


    /**
     * 通过id查询区域表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysAreaService.getById(id));
    }

    /**
     * 新增区域表
     * @param sysArea 区域表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysarea_add')")
    public Result save(@RequestBody @Valid SysArea sysArea) {
        return Result.success(sysAreaService.save(sysArea));
    }

    /**
     * 修改区域表
     * @param sysArea 区域表
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysarea_edit')")
    public Result updateById(@RequestBody @Valid SysArea sysArea) {
        return Result.success(sysAreaService.updateById(sysArea));
    }

    /**
     * 通过id删除区域表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysarea_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(sysAreaService.removeById(id));
    }

}
