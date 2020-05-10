package com.cloud.oss.conpoment;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 处理sql拦截
 * @author Aijm
 * @since 2020/3/17
 */
@Slf4j
@Component
public class ProSqlParserFilter implements ISqlParserFilter {

    @Override
    public boolean doFilter(MetaObject metaObject) {
        MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
        // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
        if ("com.cloud.oss.mapper.SysFileMapper.saveFile".equals(ms.getId())) {
            return true;
        }
        return false;
    }
}
