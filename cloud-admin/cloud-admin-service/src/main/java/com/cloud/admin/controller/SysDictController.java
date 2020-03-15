package com.cloud.admin.controller;

import com.cloud.admin.beans.dto.DictDTO;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.po.SysDict;
import com.cloud.admin.service.SysDictService;
import com.cloud.common.data.enums.ResultEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 字典表
 *
 * @author Aijm
 * @date 2019-09-05 16:53:21
 */
@RestController
@RequestMapping("/dict" )
@Api(value = "dict", tags = "sysdict管理")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysDict 字典表
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin_sysdict_view')")
    public Result getSysDictPage(Page page, SysDict sysDict) {
        return Result.success(sysDictService.page(page, Wrappers.query(sysDict)));
    }


    /**
     * 通过id查询字典表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdict_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysDictService.getById(id));
    }

    /**
     * 新增字典表
     * @param sysDict 字典表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdict_add')")
    public Result save(@RequestBody @Valid SysDict sysDict) {
        return Result.success(sysDictService.save(sysDict));
    }

    /**
     * 修改字典表
     * @param sysDict 字典表
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdict_edit')")
    public Result updateById(@RequestBody @Valid SysDict sysDict) {
        // 不能修改TypeCode
        return Result.success(sysDictService.updateById(sysDict));
    }

    /**
     * 通过id删除字典表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdict_del')")
    public Result removeById(@PathVariable Long id) {
        SysDict byId = sysDictService.getById(id);
        if (DictDTO.DICT_SYS.equals(byId.getSystem())) {
            return Result.error(ResultEnum.CRUD_DELETE_NOT);
        }
        // 需要删除关联的表数据
        return Result.success(sysDictService.removeByDict(byId));
    }

}
