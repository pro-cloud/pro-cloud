package com.cloud.oss.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.oss.beans.dto.SysFileDTO;
import com.cloud.oss.beans.po.SysFile;
import com.cloud.oss.service.SysFileService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文件统一管理
 *
 * @author Aijm
 * @date 2019-09-11 17:24:43
 */
@RestController
@RequestMapping("/sysfile" )
@Api(value = "sysfile", tags = "sysfile管理")
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysFile 文件统一管理
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('oss_sysfile_view')")
    public Result getSysFilePage(Page page, SysFileDTO sysFile) {

        LambdaQueryWrapper<SysFile> query = Wrappers.<SysFile>query().lambda()
                .like(StrUtil.isNotBlank(sysFile.getFileName()), SysFile::getFileName, sysFile.getFileName())
                .eq(StrUtil.isNotBlank(sysFile.getType()), SysFile::getType, sysFile.getType())
                .eq(StrUtil.isNotBlank(sysFile.getBelongName()), SysFile::getBelongName, sysFile.getBelongName())
                .eq(StrUtil.isNotBlank(sysFile.getBelongType()), SysFile::getBelongType, sysFile.getBelongType())
                .ge(sysFile.getCreateBegDate() != null, SysFile::getCreateDate, sysFile.getCreateBegDate())
                .lt(sysFile.getCreateEndDate()!= null, SysFile::getCreateDate, sysFile.getCreateEndDate());
        return Result.success(sysFileService.page(page, query));
    }


    /**
     * 通过id查询文件统一管理
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oss_sysfile_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysFileService.getById(id));
    }

    /**
     * 新增文件统一管理
     * @param sysFile 文件统一管理
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oss_sysfile_add')")
    public Result save(@RequestBody @Valid SysFile sysFile) {
        return Result.success(sysFileService.save(sysFile));
    }

    /**
     * 修改文件统一管理
     * @param sysFile 文件统一管理
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oss_sysfile_edit')")
    public Result updateById(@RequestBody @Valid SysFile sysFile) {
        return Result.success(sysFileService.updateById(sysFile));
    }

    /**
     * 通过id删除文件统一管理
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oss_sysfile_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(sysFileService.removeById(id));
    }

}
