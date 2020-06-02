package com.cloud.admin.util;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.po.SysOffice;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.service.SysOfficeService;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.util.var.StatusVar;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * 部门工具类
 * @author Aijm
 * @since 2019/11/14
 */
@UtilityClass
public class OfficeUtil {

    private static SysOfficeService sysOfficeService = SpringUtil.getBean(SysOfficeService.class);

    ////////////////////////// 部门相关 //////////////////////////////////////

    /**
     * 获取到当前用户 部门信息
     * @return
     */
    public static SysOffice getUserOffice() {
        SysUser user = UserUtil.getUser();
        return getOffice(user.getOfficeId());
    }

    /**
     * 根据id获取office
     * @param id
     * @return
     */
    public static SysOffice getOffice(Long id) {
        return sysOfficeService.getById(id);
    }

    /**
     * 获取所有的office 启用状态的
     * @return
     */
    public static List<SysOffice> getAllUpOffice() {
        return sysOfficeService.list(Wrappers.<SysOffice>query().lambda()
                .eq(SysOffice::getStatus, StatusVar.STATUS_UP)
                .orderByAsc(SysOffice::getSort));
    }

    /**
     * 获取所有的office
     * @return
     */
    public static List<SysOffice> getAllOffice() {
        return sysOfficeService.list(Wrappers.<SysOffice>query().lambda()
                .orderByAsc(SysOffice::getSort));
    }

    /**
     * 获取到该人员部门及以下的部门
     *  超级管理员一样 没有走缓存
     * @return
     */
    public static List<SysOffice> getOffices() {
        return getOffices(getUserOffice());
    }

    /**
     * 获取到部门及以下的部门
     * @param office 当前部门的详细信息
     * @return
     */
    public static List<SysOffice> getOffices(SysOffice office) {
        // 获取到 id以及以下的部门信息
        List<SysOffice> offices = sysOfficeService.list(Wrappers.<SysOffice>query().lambda()
                .like(SysOffice::getParentIds, office.getId()+",")
                .or()
                .eq(SysOffice::getId, office.getId())
                .orderByAsc(SysOffice::getSort));
        offices.add(office);
        return offices;
    }
}
