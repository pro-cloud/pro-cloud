package com.cloud.order;

import com.cloud.common.data.annotation.EnableProData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单demo
 * @author Aijm
 * @since 2020/3/15
 */
@SpringCloudApplication
@EnableProData
@EnableFeignClients
public class OderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OderApplication.class, args);
	}


}
