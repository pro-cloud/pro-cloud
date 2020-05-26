package com.cloud.common.data.util;

import com.cloud.common.data.user.SystemService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


/**
 * 租户工具类
 * @author Aijm
 * @since 2020/4/6
 */
@UtilityClass
@Slf4j
public class TenantUtil {


    private static final SystemService systemService = SpringUtil.getBean(SystemService.class);


    /**
     * 默认根据传递来的租户 获取到应该的租户信息
     *  可能为登录的租户id
     * @return
     */
    public String getCurrentTenant() {
        return systemService.getUserTenantIds();

    }

}
