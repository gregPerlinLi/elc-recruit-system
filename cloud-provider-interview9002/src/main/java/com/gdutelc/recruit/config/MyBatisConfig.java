package com.gdutelc.recruit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 配置类
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@Configuration
@MapperScan("com.gdutelc.recruit.mapper")
public class MyBatisConfig {
}
