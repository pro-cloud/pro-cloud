package com.cloud.txmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 事物管理器
 * @author Aijm
 * @since 2019/8/16
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudTmApplication.class, args);
    }
}
