package com.cloud.websocket;

import com.cloud.common.websocket.annotation.EnableProWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author Aijm
 * @Description 定时任务
 * @Date 2019/4/28
 */
@SpringBootApplication
@EnableProWebSocket
public class CloudWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudWebSocketApplication.class, args);
    }

}
