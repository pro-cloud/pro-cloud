package com.cloud.common.data.util;

import cn.hutool.core.collection.CollUtil;
import com.cloud.common.data.user.SystemService;
import com.cloud.common.util.util.StrUtils;
import com.cloud.common.util.var.StaticVar;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 根据传递来的租户 获取到应该的租户信息
     * @param tenantId
     * @return
     */
    public String getCurrentTenant(String tenantId) {
        String userTenantIds = systemService.getUserTenantIds();
        // 如果当前用户为空
        if (StrUtils.isBlank(userTenantIds)) {
            if (StrUtils.isBlank(tenantId)) {
                return StaticVar.TENANT_ID_DEFAULT;
            }
            return tenantId;
        }
        //  在登录了的情况下
        if (StrUtils.isBlank(tenantId)) {
            return userTenantIds;
        }
        List<String> userIds = StrUtils.splitTrim(userTenantIds, StrUtils.COMMA);
        List<String> currentIds = StrUtils.splitTrim(tenantId, StrUtils.COMMA);
        List<String> inTenantId = (List<String>) CollUtil.intersection(userIds, currentIds);

        if (CollUtil.isEmpty(inTenantId)) {
            return StaticVar.TENANT_ID_DEFAULT;
        }

        return CollUtil.join(inTenantId, StrUtils.COMMA);

    }


    /**
     * 根据传递来的租户 获取到应该的租户信息
     * @return
     */
    public String getCurrentTenant() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String tenantId = request.getHeader(StaticVar.TENANT_ID);
        log.debug("header中的租户为:{}", tenantId);
        return  getCurrentTenant(tenantId);

    }

}
