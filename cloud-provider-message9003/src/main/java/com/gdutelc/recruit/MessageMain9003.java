package com.gdutelc.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gregPerlinLi
 * @date 2022-08-05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MessageMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(MessageMain9003.class, args);
    }
}
