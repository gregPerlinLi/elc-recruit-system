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
public class RecruitMain91 {
    public static void main(String[] args) {
        SpringApplication.run(RecruitMain91.class, args);
    }
}
