package com.cloud.common.data.util;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.util.util.StrUtils;
import com.cloud.common.util.var.StaticVar;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aijm
 * @Description 获取request等
 * @Date 2020/4/23
 */
@UtilityClass
@Slf4j
public class ServletUtil {

    public static final String BEARER_TOKEN_TYPE = "Bearer ";

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 得到response对象
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();

        return response;
    }

    /**
     * 获取用户token
     * @return
     */
    public static String getToken() {
        HttpServletRequest request = ServletUtil.getRequest();
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotBlank(header) && header.startsWith(BEARER_TOKEN_TYPE)) {
            return StrUtils.removePrefix(header, BEARER_TOKEN_TYPE);
        }
        return null;
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
        return  StrUtils.isNotBlank(tenantId) ? tenantId : StaticVar.TENANT_ID_DEFAULT;

    }

    /**
     * 根据传递来的租户 获取到应该的租户信息
     * @return
     */
    public String getCurrentTenant(HttpServletRequest request) {
        String tenantId = request.getHeader(StaticVar.TENANT_ID);
        log.debug("header中的租户为:{}", tenantId);
        return StrUtils.isNotBlank(tenantId)? tenantId : StaticVar.TENANT_ID_DEFAULT;
    }

}
