package com.cloud.common.data.config;

import feign.Feign;
import feign.Logger;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 *  feign 使用okhttp
 * @author Aijm
 * @since 2019/7/31
 */
@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignOkHttpConfig {


	private int feignOkHttpReadTimeout = 600;
	private int feignConnectTimeout = 600;
	private int feignWriteTimeout = 1200;

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS).connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
				.writeTimeout(feignWriteTimeout, TimeUnit.SECONDS).connectionPool(new ConnectionPool())
//				 .addInterceptor(okHttpLoggingInterceptor)
				.build();
	}

	@Bean
	Logger.Level feignLogger(){
		return Logger.Level.FULL;
	}
}
