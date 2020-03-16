package com.cloud.common.data.tenant;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.util.var.StaticVar;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
public class TenantContextHolderFilter extends GenericFilterBean {

	@Override
	@SneakyThrows
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String tenantId = request.getHeader(StaticVar.TENANT_ID);
		log.debug("header中的租户为:{}", tenantId);
		if (StrUtil.isNotBlank(tenantId)) {
			TenantContextHolder.setTenantId(tenantId);
		} else {
			TenantContextHolder.setTenantId(StaticVar.TENANT_ID_DEFAULT);
		}
		filterChain.doFilter(request, response);
		TenantContextHolder.clearTenantIds();
	}
}
