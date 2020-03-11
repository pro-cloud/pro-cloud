package com.cloud.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author Aijm
 * @Description 定时任务
 * @Date 2019/4/28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.job","com.cloud.common.job"})
public class CloudJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudJobApplication.class, args);
    }

}
