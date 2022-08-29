package com.gdutelc.recruit.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义 {@link RestTemplate} 配置类，使其支持负载均衡
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 配置 {@link RestTemplate} 对负载均衡的支持
     * @return 支持负载均衡的 {@link RestTemplate}
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
