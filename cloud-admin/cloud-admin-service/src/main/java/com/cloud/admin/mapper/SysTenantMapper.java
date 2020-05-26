package com.cloud.admin.mapper;

import com.cloud.common.data.base.ProMapper;
import com.cloud.admin.beans.po.SysTenant;

/**
 * 租户管理
 *
 * @author Aijm
 * @date 2020-05-25 13:32:23
 */
public interface SysTenantMapper extends ProMapper<SysTenant> {

    /**
     * 获取下一个租户id
     * @return
     */
    Integer getNextTenantId();

}
