package com.cloud.generator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.Result;
import com.cloud.generator.entity.GenDataSource;
import com.cloud.generator.service.GenDataSourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 生成代码数据源
 *
 * @author Aijm
 * @date 2020-05-14 23:00:48
 */
@RestController
@RequestMapping("/gendatasource" )
@Api(value = "gendatasource", tags = "gendatasource管理")
public class GenDataSourceController {

    @Autowired
    private GenDataSourceService genDataSourceService;


    /**
     * 通过id查询生成代码数据源
     * @param id id
     * @return Result
     */
    @GetMapping("/init")
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_view')")
    public Result dataSourceInit(@PathVariable("id") Long id) {

        return Result.success(genDataSourceService.getById(id));
    }


    /**
     * 分页查询
     * @param page 分页对象
     * @param genDataSource 生成代码数据源
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_view')")
    public Result getGenDataSourcePage(Page page, GenDataSource genDataSource) {
        return Result.success(genDataSourceService.page(page, Wrappers.query(genDataSource)));
    }


    /**
     * 通过id查询生成代码数据源
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(genDataSourceService.getById(id));
    }

    /**
     * 新增生成代码数据源
     * @param genDataSource 生成代码数据源
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_add')")
    public Result save(@RequestBody @Valid GenDataSource genDataSource) {
        return Result.success(genDataSourceService.save(genDataSource));
    }

    /**
     * 修改生成代码数据源
     * @param genDataSource 生成代码数据源
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_edit')")
    public Result updateById(@RequestBody @Valid GenDataSource genDataSource) {
        return Result.success(genDataSourceService.updateById(genDataSource));
    }

    /**
     * 通过id删除生成代码数据源
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('generator_gendatasource_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(genDataSourceService.removeById(id));
    }

}
