package com.cloud.admin.controller;

import com.cloud.admin.util.DictUtil;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.po.SysDictList;
import com.cloud.admin.service.SysDictListService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 字典项list typeCode 必须遵守'list_'开头
 *
 * @author Aijm
 * @date 2019-09-05 19:52:37
 */
@RestController
@RequestMapping("/dictList" )
@Api(value = "dictList", tags = "sysdictlist管理")
public class SysDictListController {

    @Autowired
    private SysDictListService sysDictListService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysDictList 字典项list
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_view')")
    public Result getSysDictListPage(Page page, SysDictList sysDictList) {
        return Result.success(sysDictListService.page(page, Wrappers.query(sysDictList)
                .lambda()
                .orderByAsc(SysDictList::getSort)));
    }


    /**
     * 通过id查询字典项list
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysDictListService.getById(id));
    }

    /**
     * 通过typecode查询字典项list
     * @param typeCode typeCode
     * @return Result
     */
    @GetMapping("/type/{typeCode}")
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_view')")
    @ApiOperation(value = "任务字典typeCode查询", notes = "任务字典typeCode查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeCode", value = "字典类型", required = true, dataType = "String", paramType = "query")
    })
    public Result getByTypeCode(@PathVariable("typeCode") String typeCode) {
        return Result.success(DictUtil.getDictLists(typeCode,null));
    }

    /**
     * 通过typecode查询字典项list label 区分标签
     * @param typeCode typeCode
     * @param label label
     * @return Result
     */
    @GetMapping("/type/{typeCode}/{label}")
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_view')")
    public Result getByTypeCodeLabel(@PathVariable("typeCode") String typeCode,@PathVariable("label") String label) {
        return Result.success(DictUtil.getDictLists(typeCode, label));
    }

    /**
     * 新增字典项list
     * @param sysDictList 字典项list
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_add')")
    public Result save(@RequestBody @Valid SysDictList sysDictList) {
        return Result.success(sysDictListService.save(sysDictList));
    }

    /**
     * 修改字典项list
     * @param sysDictList 字典项list
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_edit')")
    public Result updateById(@RequestBody @Valid SysDictList sysDictList) {
        return Result.success(sysDictListService.updateById(sysDictList));
    }

    /**
     * 通过id删除字典项list
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdictlist_del')")
    public Result removeById(@PathVariable Long id) {
        SysDictList byId = sysDictListService.getById(id);
        return Result.success(sysDictListService.removeByDictList(byId));
    }

}
