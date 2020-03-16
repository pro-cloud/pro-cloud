package com.cloud.storage;

import com.cloud.common.data.annotation.EnableProData;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Storage应用demo
 * @author Aijm
 * @since 2020/3/15
 */
@SpringCloudApplication
@EnableProData
@EnableFeignClients
public class StorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
	}

}
