package com.cloud.auth;

import com.cloud.common.cache.annotation.EnableProCache;
import com.cloud.common.data.annotation.EnableProData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Aijm
 * @Description 认证模块
 * @Date 2019/4/28
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.cloud.auth", "com.cloud.common.oauth"})
@EnableProData
@EnableProCache
public class CloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

}
