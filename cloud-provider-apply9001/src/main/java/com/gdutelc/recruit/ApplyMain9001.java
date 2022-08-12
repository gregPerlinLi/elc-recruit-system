package com.gdutelc.recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gregPerlinLi
 * @date 2022-08-04
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.gdutelc.recruit.mapper")
public class ApplyMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(ApplyMain9001.class, args);
    }
}
