package com.cloud.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.util.OfficeUtil;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.data.base.Result;
import com.cloud.admin.beans.po.SysOffice;
import com.cloud.admin.service.SysOfficeService;
import com.cloud.common.data.enums.ResultEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 机构表
 *
 * @author Aijm
 * @date 2019-08-25 20:45:42
 */
@RestController
@RequestMapping("/office" )
@Api(value = "office", tags = "sysoffice管理")
public class SysOfficeController {

    @Autowired
    private SysOfficeService sysOfficeService;

    /**
     * 查询 所有启用的部门
     * @return
     */
    @GetMapping("/listALL")
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_view')")
    public Result getSysOfficeAll() {
        return Result.success(OfficeUtil.getAllUpOffice());
    }

    /**
     * 根据菜单名称查询
     * @return
     */
    @GetMapping("/findList")
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_view')")
    public Result findList(SysOffice sysOffice) {
        List<SysOffice> offices = sysOfficeService.list(Wrappers.<SysOffice>query().lambda()
                .like(SysOffice::getName, sysOffice.getName()).orderByAsc(SysOffice::getSort));
        return Result.success(offices);
    }
    /**
     * 通过id查询机构表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysOfficeService.getById(id));
    }

    /**
     * 新增机构表
     * @param sysOffice 机构表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_add')")
    public Result save(@RequestBody @Valid SysOffice sysOffice) {
        if (chealDataScope(sysOffice.getParentId())) {
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        return Result.success(sysOfficeService.save(sysOffice));
    }

    /**
     * 修改机构表
     * @param sysOffice 机构表
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_edit')")
    public Result updateById(@RequestBody @Valid SysOffice sysOffice) {
        // 判断是否具有操作权限
        SysOffice office = OfficeUtil.getOffice(sysOffice.getId());
        if (chealDataScope(office.getParentId())) {
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        sysOffice.setStatus(null);
        return Result.success(sysOfficeService.updateById(sysOffice));
    }

    /**
     * 通过id删除机构表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_del')")
    public Result removeById(@PathVariable Long id) {
        // 判断是否具有操作数据权限
        SysOffice office = OfficeUtil.getOffice(id);
        if (chealDataScope(office.getParentId())) {
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        // 判断是否还有子节点
        List<SysOffice> list = sysOfficeService.list(Wrappers.<SysOffice>query().lambda()
                .like(SysOffice::getParentIds, id + ","));
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error(ResultEnum.CRUD_DELETE_NOT);
        }
        return Result.success(sysOfficeService.removeById(id));
    }


    /**
     *  弹出树
     * @param extId 表示当前节点的id  (排除了自身extId)
     * @return
     */
    @PreAuthorize("@pms.hasPermission('admin_sysoffice_view')")
    @GetMapping(value = "treeData")
    public Result treeData(@RequestParam(required=false) String extId) {

        List<SysOffice> retList = Lists.newArrayList();
        // 根据用户信息查询到的office
        List<SysOffice> list = OfficeUtil.getAllUpOffice();

        for (SysOffice sysOffice : list) {
            boolean hasExtId = extId != null && !extId.equals(sysOffice.getId().toString()) && sysOffice.getParentIds().indexOf("," + extId + ",") == -1;
            if (StrUtil.isBlank(extId) || hasExtId ){
                retList.add(sysOffice);
            }
        }
        return Result.success(retList);
    }


    /**
     * 判断是否含有修改/删除/添加数据权限
     * @param parentId
     * @return
     */
    private boolean chealDataScope(Long parentId) {
        if (!UserUtil.hasAdmin()){
            SysOffice userOffice = OfficeUtil.getUserOffice();
            // 父节点详细信息
            SysOffice office = OfficeUtil.getOffice(parentId);
            if (office.getParentIds().contains(userOffice.getParentIds())) {
                return true;
            }
        }
        return false;
    }
}
