package com.cloud.auth.service;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * 实现mysql 管理数据库
 * @author Aijm
 * @since 2019/7/27
 */
@Service
public class ProJdbcClientDetailsService extends JdbcClientDetailsService {

    public ProJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }
}
