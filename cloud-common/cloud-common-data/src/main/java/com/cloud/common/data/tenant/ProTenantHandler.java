package com.cloud.common.data.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.cloud.common.data.util.TenantUtil;
import com.cloud.common.util.util.StrUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户处理
 * @author Aijm
 * @since 2020/1/14
 */
@Slf4j
public class ProTenantHandler implements TenantHandler {

	@Autowired
	private ProTenantProps propes;

	/**
	 * 租户值
	 * @return
	 */
	@Override
	public Expression getTenantId(boolean where) {
		String tenantId = TenantUtil.getCurrentTenant();
		log.debug("当前租户的值为:{}", tenantId);
		return new LongValue(tenantId);
	}

	/**
	 * 获取租户字段名
	 *
	 * @return 租户字段名
	 */
	@Override
	public String getTenantIdColumn() {
		return propes.getColumn();
	}

	/**
	 * 根据表名判断是否进行过滤
	 * @param tableName
	 * @return
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		return !propes.getTables().contains(tableName);
	}



}
