package com.cloud.common.data.tenant;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 处理sql拦截
 * @author Aijm
 * @since 2020/3/17
 */
@Slf4j
public class ProSqlParserFilter implements ISqlParserFilter {

    @Override
    public boolean doFilter(MetaObject metaObject) {

        return false;
    }
}
