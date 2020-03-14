package com.cloud.auth;

import com.cloud.common.cache.annotation.EnableProCache;
import com.cloud.common.data.annotation.EnableProData;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 * @Author Aijm
 * @Description 认证模块
 * @Date 2019/4/28
 */
@SpringCloudApplication
@EnableProData
@EnableProCache
public class CloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

}
