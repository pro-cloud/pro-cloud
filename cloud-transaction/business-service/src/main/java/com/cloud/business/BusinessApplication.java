package com.cloud.business;

import com.cloud.common.data.annotation.EnableProData;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Business 应用
 * @author Aijm
 * @since 2020/3/15
 */
@SpringCloudApplication
@EnableProData
@EnableFeignClients
public class BusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}


}
