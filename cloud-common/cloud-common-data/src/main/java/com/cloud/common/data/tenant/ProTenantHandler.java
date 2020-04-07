package com.cloud.common.data.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
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
		String tenantIds = TenantContextHolder.getTenantIds();
		log.debug("当前租户的值为:{}", tenantIds);
//		if (StrUtils.containsIgnoreCase(tenantIds, StrUtils.COMMA)) {
//			return multipleTenantIdCondition(tenantIds);
//		}
		return new LongValue(tenantIds);
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


	/**
	 * 多租户时的处理
	 * @param tenantIds
	 * @return
	 */
	private Expression multipleTenantIdCondition(String tenantIds) {
		final InExpression inExpression = new InExpression();
		inExpression.setLeftExpression(new Column(getTenantIdColumn()));
		final ExpressionList itemsList = new ExpressionList();
		// 租户集合
		String[] ids = StrUtils.split(tenantIds, StrUtils.COMMA);
		List<Expression> inValues = new ArrayList<>(ids.length);
		for (String tenantId: ids) {
			inValues.add(new LongValue(tenantId));
		}
		itemsList.setExpressions(inValues);
		inExpression.setRightItemsList(itemsList);
		return inExpression;
	}

}
