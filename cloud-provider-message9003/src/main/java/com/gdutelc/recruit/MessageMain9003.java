package com.gdutelc.recruit;

import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.domain.wx.AccessTokenDTO;
import com.gdutelc.recruit.service.impl.ExportServiceImpl;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import com.gdutelc.recruit.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.File;

/**
 * @author gregPerlinLi
 * @date 2022-08-05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class MessageMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(MessageMain9003.class, args);
        Init init = SpringUtils.getBeanByClazz(Init.class);
        init.checkProcessKey();
        init.initAccessToken();
        init.createExportFile();
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

    @Resource
    WeChatServerService weChatServerService;

    public void initAccessToken(){
        AccessTokenDTO accessTokenDTO = weChatServerService.refreshAccessToken();
        if(accessTokenDTO == null || accessTokenDTO.getAccessToken() == null){
            log.error("微信小程序全局唯一后台接口调用凭据AccessToken初始化失败!");
        }else {
            log.info("微信小程序全局唯一后台接口调用凭据AccessToken初始化成功");
        }
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void checkProcessKey(){
        if(Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisKeyConstant.PROCESS))){
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,"10");
            log.warn("Redis数据库中不存在面试总体进度值，已自动添加...");
        }
    }


    public void createExportFile() {
        File file = new File(ExportServiceImpl.TMP_PATH);
        if(!file.exists()) {
            file.mkdir();
        }
    }
}
