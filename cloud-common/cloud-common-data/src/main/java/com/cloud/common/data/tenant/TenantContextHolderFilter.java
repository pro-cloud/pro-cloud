package com.cloud.common.data.tenant;

import com.cloud.common.data.util.TenantUtil;
import com.cloud.common.util.var.StaticVar;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aijm
 * @Description 租户拦截器
 * @Date 2020/1/15
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends OncePerRequestFilter {

	/**
	 * 对租户进行处理 添加默认组合
	 * @param request
	 * @param response
	 * @param filterChain
	 */
	@Override
	@SneakyThrows
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

		String tenantId = request.getHeader(StaticVar.TENANT_ID);
		log.debug("header中的租户为:{}", tenantId);
		TenantContextHolder.setTenantId(TenantUtil.getCurrentTenant(tenantId));
		log.debug("最终的租户为:{}", TenantContextHolder.getTenantIds());
		filterChain.doFilter(request, response);
		TenantContextHolder.clearTenantIds();
	}
}
