package com.cloud.admin.service;

import com.cloud.common.data.base.IProService;
import com.cloud.admin.beans.po.SysTenant;

/**
 * 租户管理
 *
 * @author Aijm
 * @date 2020-05-25 13:32:23
 */
public interface SysTenantService extends IProService<SysTenant> {

    /**
     * 获取下一个租户id
     * @return
     */
    Integer getNextTenantId();
}
