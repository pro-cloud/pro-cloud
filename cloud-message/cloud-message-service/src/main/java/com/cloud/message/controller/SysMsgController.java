package com.cloud.message.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.data.base.Result;

import com.cloud.message.beans.po.SysMsg;
import com.cloud.message.service.SysMsgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 短信发送消息日志
 *
 * @author Aijm
 * @date 2019-09-10 13:45:16
 */
@RestController
@RequestMapping("/sysmsg" )
@Api(value = "sysmsg", tags = "sysmsg管理")
public class SysMsgController {

    @Autowired
    private SysMsgService sysMsgService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysMsg 短信发送消息日志
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sms_sysmsg_view')")
    public Result getSysMsgPage(Page page, SysMsg sysMsg) {
        return Result.success(sysMsgService.page(page, Wrappers.query(sysMsg)));
    }


    /**
     * 通过id查询短信发送消息日志
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sms_sysmsg_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysMsgService.getById(id));
    }

    /**
     * 新增短信发送消息日志
     * @param sysMsg 短信发送消息日志
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sms_sysmsg_add')")
    public Result save(@RequestBody SysMsg sysMsg) {
        return Result.success(sysMsgService.save(sysMsg));
    }

    /**
     * 修改短信发送消息日志
     * @param sysMsg 短信发送消息日志
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sms_sysmsg_edit')")
    public Result updateById(@RequestBody SysMsg sysMsg) {
        return Result.success(sysMsgService.updateById(sysMsg));
    }

    /**
     * 通过id删除短信发送消息日志
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sms_sysmsg_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(sysMsgService.removeById(id));
    }

}
