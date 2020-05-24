package com.cloud.generator.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.exception.BaseException;
import com.cloud.common.data.util.FileDownUtil;
import com.cloud.generator.entity.GenScheme;
import com.cloud.generator.entity.TableColumn;
import com.cloud.generator.service.GenSchemeService;
import com.cloud.generator.service.TableColumnService;
import com.cloud.generator.util.DynamicDataSourceHolder;
import com.cloud.generator.util.GenUtils;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

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
    @Autowired
    private TableColumnService tableColumnService;

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
     *  生成代码
     * @param id
     */
    @SneakyThrows
    @GetMapping("/generator/{id}")
//    @PreAuthorize("@pms.hasPermission('generator_genscheme_add')")
    public void  generator(@PathVariable("id") Long id) {
        GenScheme byId = genSchemeService.getById(id);
        DynamicDataSourceHolder.setDataSourceType(Long.toString(byId.getSourceId()));
        List<TableColumn> tableColumnList = tableColumnService.getTableColumnList(byId.getTableName());

        // 获取表信息
        if (StrUtil.isBlank(byId.getRemarks())) {
            String tableComment = tableColumnService.queryTableInfo(byId.getTableName());
            byId.setRemarks(tableComment);
        }
        if (StrUtil.isBlank(byId.getClassName())) {
            String tableName = byId.getTableName();
            byId.setClassName(StrUtil.toCamelCase(tableName));
        }

        DynamicDataSourceHolder.clearDataSourceType();
        if (CollUtil.isEmpty(tableColumnList)) {
            throw new BaseException("请输入争取表名");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);

        // 生成代码
        GenUtils.generatorCode(byId, tableColumnList, zip);
        IoUtil.close(zip);

        FileDownUtil.write( byId.getTableName()+".zip", out.toByteArray());
    }


}
