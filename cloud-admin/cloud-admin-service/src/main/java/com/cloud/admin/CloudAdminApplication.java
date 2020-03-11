package com.cloud.admin;

import com.cloud.common.cache.annotation.EnableProCache;
import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 *   基础模块
 * @author Aijm
 * @since  2019/5/8
 */
@SpringCloudApplication
@EnableProSecurtity
@EnableProData
@EnableProCache
public class CloudAdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }



}




