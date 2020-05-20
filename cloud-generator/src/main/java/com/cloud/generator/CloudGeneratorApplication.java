package com.cloud.generator;

import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *  代码生成
 * @author Aijm
 * @since  2019/5/8
 */
@SpringCloudApplication
@EnableProData
@EnableProSecurtity
@EnableFeignClients(basePackages ="com.cloud.admin.api")
@ComponentScan({"com.cloud.admin", "com.cloud.generator"})
public class CloudGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGeneratorApplication.class, args);
    }

}
