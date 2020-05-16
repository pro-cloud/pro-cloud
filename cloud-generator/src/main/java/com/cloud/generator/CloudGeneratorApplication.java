package com.cloud.generator;

import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  代码生成
 * @author Aijm
 * @since  2019/5/8
 */
@SpringBootApplication
@EnableProData
@EnableProSecurtity
public class CloudGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGeneratorApplication.class, args);
    }

}
