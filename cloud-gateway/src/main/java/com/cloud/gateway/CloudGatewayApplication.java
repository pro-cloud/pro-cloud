package com.cloud.gateway;

import com.cloud.common.cache.annotation.EnableProCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *  网关模块
 * @author Aijm
 * @since  2019/5/8
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableProCache
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

}
