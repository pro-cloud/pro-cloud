package com.cloud.monitor;

import com.cloud.common.data.annotation.EnableProData;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Aijm
 * @Description admin 监控
 * @Date 2019/8/6
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
@EnableProData
public class AdminMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminMonitorApplication.class, args);
    }
}
