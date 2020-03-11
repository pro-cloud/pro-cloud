package com.cloud.common.data.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 租户处理
 * @author Aijm
 * @since 2020/1/14
 */
@Slf4j
public class ProTenantHandler implements TenantHandler {

	@Autowired
	private ProTenantProperties properties;


	/**
	 * 租户值
	 * @return
	 */
	@Override
	public Expression getTenantId(boolean where) {
		Integer tenantId = TenantContextHolder.getTenantId();
		log.debug("当前租户的值为:{}", tenantId);
		if (tenantId == null) {
			return new NullValue();
		}
		return new LongValue(tenantId);
	}

	/**
	 * 获取租户字段名
	 *
	 * @return 租户字段名
	 */
	@Override
	public String getTenantIdColumn() {
		return properties.getColumn();
	}

	/**
	 * 根据表名判断是否进行过滤
	 * @param tableName
	 * @return
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		Integer tenantId = TenantContextHolder.getTenantId();
		// 租户中ID 为空，查询全部，不进行过滤
		if (tenantId == null) {
			return Boolean.TRUE;
		}

		return !properties.getTables().contains(tableName);
	}
}
