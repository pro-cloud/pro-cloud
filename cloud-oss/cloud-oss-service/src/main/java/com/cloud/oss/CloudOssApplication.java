package com.cloud.oss;

import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.oss.annotation.EnableProOss;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *   基础邮件模块
 * @author Aijm
 * @since  2019/5/8
 */
@SpringCloudApplication
@EnableProData
@EnableProSecurtity
@EnableProOss
@EnableFeignClients(basePackages ="com.cloud.admin.api")
@ComponentScan({"com.cloud.admin", "com.cloud.oss"})
public class CloudOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOssApplication.class, args);
    }


}
