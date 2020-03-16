package com.cloud.message.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.Result;

import com.cloud.message.beans.po.SysMsgConfig;
import com.cloud.message.service.SysMsgConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 短信配置信息
 *
 * @author Aijm
 * @date 2019-09-10 13:40:14
 */
@RestController
@RequestMapping("/sysmsgconfig" )
@Api(value = "sysmsgconfig", tags = "sysmsgconfig管理")
public class SysMsgConfigController {

    @Autowired
    private SysMsgConfigService sysMsgConfigService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysMsgConfig 短信配置信息
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sms_sysmsgconfig_view')")
    public Result getSysMsgConfigPage(Page page, SysMsgConfig sysMsgConfig) {
        return Result.success(sysMsgConfigService.page(page, Wrappers.query(sysMsgConfig)));
    }


    /**
     * 通过id查询短信配置信息
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sms_sysmsgconfig_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysMsgConfigService.getById(id));
    }

    /**
     * 新增短信配置信息
     * @param sysMsgConfig 短信配置信息
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sms_sysmsgconfig_add')")
    public Result save(@RequestBody SysMsgConfig sysMsgConfig) {
        return Result.success(sysMsgConfigService.save(sysMsgConfig));
    }

    /**
     * 修改短信配置信息
     * @param sysMsgConfig 短信配置信息
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sms_sysmsgconfig_edit')")
    public Result updateById(@RequestBody SysMsgConfig sysMsgConfig) {
        return Result.success(sysMsgConfigService.updateById(sysMsgConfig));
    }

    /**
     * 通过id删除短信配置信息
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sms_sysmsgconfig_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(sysMsgConfigService.removeById(id));
    }

}
