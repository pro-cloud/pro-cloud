package com.cloud.auth.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.constant.StatusVar;
import com.cloud.auth.entity.SysTenant;
import com.cloud.auth.service.SysTenantService;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.util.RedisUtil;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.data.exception.BaseException;
import com.cloud.common.data.util.ServletUtil;
import com.cloud.common.data.util.TenantUtil;
import com.cloud.common.oauth.exception.TentantException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理租户是否停用
 * @author Aijm
 * @since 2020/5/24
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantSatausFilter extends OncePerRequestFilter {

    @Autowired
    private SysTenantService sysTenantService;

    /**
     * 失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //  优先走缓存 拿取数据
        SysTenant sysTenant = (SysTenant)RedisUtil.get(CacheScope.TENTANT_KEY.getCacheName(), ServletUtil.getCurrentTenant(request));
        if (sysTenant == null) {
            sysTenant = sysTenantService.getOne(Wrappers.<SysTenant>query()
                    .lambda()
                    .eq(SysTenant::getTenantId, ServletUtil.getCurrentTenant(request)));
            RedisUtil.put(CacheScope.TENTANT_KEY.getCacheName(), ServletUtil.getCurrentTenant(request), sysTenant);
        }
        if (sysTenant == null || sysTenant.getStatus().intValue() == StatusVar.STATUS_DOWN.intValue()) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, new TentantException("租户停用中!"));
            return;
        }
        chain.doFilter(request, response);
    }
}
