package com.cloud.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.common.util.base.Result;
import com.cloud.admin.beans.po.SysDictTree;
import com.cloud.admin.service.SysDictTreeService;
import com.cloud.common.util.enums.ResultEnum;
import com.google.common.collect.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典表树 typeCode 必须遵守'tree_'开头
 *
 * @author Aijm
 * @date 2019-09-05 20:00:25
 */
@RestController
@RequestMapping("/dicttree" )
@Api(value = "dicttree", tags = "sysdicttree管理")
public class SysDictTreeController {

    @Autowired
    private SysDictTreeService sysDictTreeService;


    /**
     * 通过id查询字典表树
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysDictTreeService.getById(id));
    }

    /**
     * 通过typecode查询字典项list 树结构
     * @param typeCode typeCode
     * @return Result
     */
    @GetMapping("/type/{typeCode}")
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_view')")
    public Result getByTypeCode(@PathVariable("typeCode") String typeCode) {
        // 已经变成了树形结构
        return Result.success(sysDictTreeService.getDicTreeByType(typeCode));
    }
    /**
     * 新增字典表树
     * @param sysDictTree 字典表树
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_add')")
    public Result save(@RequestBody @Valid SysDictTree sysDictTree) {
        return Result.success(sysDictTreeService.save(sysDictTree));
    }

    /**
     * 修改字典表树
     * @param sysDictTree 字典表树
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_edit')")
    public Result updateById(@RequestBody @Valid SysDictTree sysDictTree) {
        return Result.success(sysDictTreeService.updateById(sysDictTree));
    }

    /**
     * 通过id删除字典表树
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_del')")
    public Result removeById(@PathVariable Long id) {
        SysDictTree byId = sysDictTreeService.getById(id);
        List<SysDictTree> list = sysDictTreeService.list(Wrappers.<SysDictTree>query().lambda()
                .like(SysDictTree::getParentIds, id + ","));
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error(ResultEnum.CRUD_DELETE_NOT);
        }
        return Result.success(sysDictTreeService.removeByDictTree(byId));
    }

    /**
     * 通过typecode查询字典项list
     * @param typeCode typeCode
     * @return Result
     */
    @GetMapping("/type/{typeCode}/{extId}")
    @PreAuthorize("@pms.hasPermission('admin_sysdicttree_view')")
    public Result getTypeCodeList(@PathVariable("typeCode") String typeCode, @PathVariable("extId") Long extId) {
        List<SysDictTree> dicTreeByType = sysDictTreeService.getDicTreeByType(typeCode);
        List<SysDictTree> list = Lists.newArrayList();
        for (SysDictTree sysDictTree : dicTreeByType) {
            if (extId.equals(sysDictTree.getParentId())) {
                list.add(sysDictTree);
            }
        }
        return Result.success(list);
    }

}
