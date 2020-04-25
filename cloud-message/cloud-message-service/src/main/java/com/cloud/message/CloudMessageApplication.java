package com.cloud.message;

import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 *   基础邮件模块
 * @author Aijm
 * @since  2019/5/8
 */
@SpringCloudApplication
@EnableProData
@EnableProSecurtity
public class CloudMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMessageApplication.class, args);
    }


}
