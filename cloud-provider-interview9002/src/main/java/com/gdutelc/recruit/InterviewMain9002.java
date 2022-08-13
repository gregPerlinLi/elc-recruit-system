package com.gdutelc.recruit;

import com.gdutelc.recruit.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
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
public class InterviewMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(InterviewMain9002.class, args);
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
@Slf4j
class Init {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void checkProcessKey(){
        if(Boolean.FALSE.equals(stringRedisTemplate.hasKey("process"))){
            stringRedisTemplate.opsForValue().set("process","10");
            log.warn("Redis数据库中不存在面试总体进度值，已自动添加...");
        }
    }
}
