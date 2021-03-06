package com.cloud.common.data.mybatis;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.cloud.common.data.tenant.ProSqlParserFilter;
import com.cloud.common.data.tenant.ProTenantHandler;
import com.cloud.common.data.tenant.ProTenantSqlParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Aijm
 * @Description 集成mybatisplus
 * @Date 2019/5/8
 */
@Configuration
@MapperScan("com.cloud.*.mapper")
public class MybatisPlusConfig {


    /**
     * 创建租户维护处理器对象
     *
     * @return 处理后的租户维护处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public TenantHandler tenantHandler() {
        return new ProTenantHandler();
    }

    /**
     * 拦截sql
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ISqlParserFilter sqlParserFilter() {
        return new ProSqlParserFilter();
    }

    /**
     * 分页插件
     * @param tenantHandler 租户处理器
     * @return PaginationInterceptor
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnExpression("${mybatisPlus.tenantEnable:true}")
    public PaginationInterceptor paginationInterceptor(TenantHandler tenantHandler, ISqlParserFilter sqlParserFilter) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new ProTenantSqlParser();
        tenantSqlParser.setTenantHandler(tenantHandler);
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(sqlParserFilter);
        return paginationInterceptor;
    }

    @Bean
    public IdentifierGenerator idGenerator() {
        return new CustomIdGenerator();
    }

    /**
     * 乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
