package com.gdutelc.recruit.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信服务端调用参数配置
 * @author cherry_jerry
 * @date 2022/08/29 13:12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat-server")
public class WeChatServerConfig {
    /**
     * 小程序唯一凭证
     */
    private String appid;
    /**
     * 小程序唯一凭证密钥
     */
    private String secret;
    /**
     *  授权类型
     */
    private String grantType;
    /**
     * 获取小程序全局唯一后台接口调用凭据的请求地址
     */
    private String tokenUrl;
    /**
     * 小程序向用户推送信息请求地址
     */
    private String sendMessageUrl;
}
