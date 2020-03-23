package com.cloud.monitor;

import com.cloud.common.data.annotation.EnableProData;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Aijm
 * @Description admin 监控
 * 如果开启的话 请在cloud-common-data 模块中将注释spring-boot-admin-starter-client  jar 开启
 * 特别注意 不建议再生产上使用 建议使用,prometheus+grafana+alertManager
 * @Date 2019/8/6
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
@EnableProData
public class ProMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProMonitorApplication.class, args);
    }
}
