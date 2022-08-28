package com.gdutelc.recruit.config;

import com.gdutelc.recruit.interceptor.AdminLoginInterceptor;
import com.gdutelc.recruit.interceptor.InterviewerLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC配置
 *
 * @author gregPerlinLi
 * @date 2022-08-16
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterviewerLoginInterceptor())
                .addPathPatterns("/interview/**");
        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/super_admin/**");
    }
}
