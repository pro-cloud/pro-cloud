package com.cloud.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.util.ServletUtil;
import com.cloud.generator.entity.GenScheme;
import com.cloud.generator.service.GenSchemeService;
import com.google.common.net.HttpHeaders;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 生成代码
 *
 * @author Aijm
 * @date 2020-05-14 22:44:46
 */
@RestController
@RequestMapping("/genscheme" )
@Api(value = "genscheme", tags = "genscheme管理")
public class GenSchemeController {

    @Autowired
    private GenSchemeService genSchemeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param genScheme 生成代码
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('generator_genscheme_view')")
    public Result getGenSchemePage(Page page, GenScheme genScheme) {
        return Result.success(genSchemeService.page(page, Wrappers.query(genScheme)));
    }


    /**
     * 通过id查询生成代码
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('generator_genscheme_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(genSchemeService.getById(id));
    }

    /**
     * 新增生成代码
     * @param genScheme 生成代码
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_genscheme_add')")
    public Result save(@RequestBody @Valid GenScheme genScheme) {
        return Result.success(genSchemeService.save(genScheme));
    }

    /**
     * 修改生成代码
     * @param genScheme 生成代码
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_genscheme_edit')")
    public Result updateById(@RequestBody @Valid GenScheme genScheme) {
        return Result.success(genSchemeService.updateById(genScheme));
    }

    /**
     * 通过id删除生成代码
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('generator_genscheme_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(genSchemeService.removeById(id));
    }



    /**
     * 生成代码
     * @param id 生成代码
     * @return Result
     */
    @SneakyThrows
    @PostMapping("/generator/{id}")
    @PreAuthorize("@pms.hasPermission('generator_genscheme_add')")
    public void  generator(@PathVariable("id") Long id) {
        GenScheme byId = genSchemeService.getById(id);
        HttpServletResponse response = ServletUtil.getResponse();
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", byId.getTableName()));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, null);

    }


}
