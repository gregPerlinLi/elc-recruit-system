package com.gdutelc.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gregPerlinLi
 * @date 2022-08-04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InterviewMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(InterviewMain9002.class, args);
    }
}
