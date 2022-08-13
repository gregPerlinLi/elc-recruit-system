package com.gdutelc.recruit;

import com.gdutelc.recruit.utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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
        Init init = SpringUtils.getBeanByClazz(Init.class);
        init.checkProcessKey();
    }
}

/**
 * 初始化类
 * @author TUFSolareyes
 * @date 2022-08-13
 */
@Component
class Init {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void checkProcessKey(){
        if(Boolean.FALSE.equals(stringRedisTemplate.hasKey("process"))){
            stringRedisTemplate.opsForValue().set("process","10");
        }
    }
}
