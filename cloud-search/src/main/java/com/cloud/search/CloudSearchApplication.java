package com.cloud.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**r
 *   基础模块
 * @author Aijm
 * @since  2019/5/8
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages ="com.cloud.admin.api")
@ComponentScan({"com.cloud.search","com.cloud.common","com.cloud.admin.api"})
public class CloudSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSearchApplication.class, args);
    }

}
