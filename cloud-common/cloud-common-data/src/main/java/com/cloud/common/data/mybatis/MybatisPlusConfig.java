package com.cloud.common.data.mybatis;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.cloud.common.data.tenant.ProTenantHandler;
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
     *  分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(10);
        return paginationInterceptor;
    }

    /**
     * 创建租户维护处理器对象
     *
     * @return 处理后的租户维护处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public ProTenantHandler proTenantHandler() {
        return new ProTenantHandler();
    }

    /**
     * 分页插件
     * @param tenantHandler 租户处理器
     * @return PaginationInterceptor
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnExpression("${mybatisPlus.tenantEnable:true}")
    public PaginationInterceptor paginationInterceptor(ProTenantHandler tenantHandler) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(tenantHandler);
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

//    /**
//     * 性能分析拦截器，不建议生产使用
//     */
//    @Bean
//    @ConditionalOnExpression("${mybatis-plus.performanceInterceptor.enabled:false}")
//    public PerformanceInterceptor performanceInterceptor(){
//        return new PerformanceInterceptor();
//    }

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
