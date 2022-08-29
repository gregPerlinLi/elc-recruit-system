package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.domain.wx.AccessTokenDTO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author cherry_jerry
 * @date 2022/08/29 16:50
 */
@Slf4j
@Service
public class WeChatServerServiceImpl implements WeChatServerService {

    @Resource
    private  RestTemplate restTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Value("${wechat-server.appid}")
    private String appId;
    @Value("${wechat-server.secret}")
    private String appSecret;
    @Value("${wechat-server.grantType}")
    private String grantType;
    @Value("${wechat-server.tokenUrl}")
    private String tokenUrl;

    @Override
    public AccessTokenDTO refreshAccessToken() {
        String url = tokenUrl
                +"?grant_type="+grantType
                +"&appid="+appId
                +"&secret="+appSecret;
        AccessTokenDTO accessTokenDTO = restTemplate.getForObject(url, AccessTokenDTO.class);
        if(accessTokenDTO == null){
            log.error("微信接口调用凭据AccessToken刷新失败,accessTokenDTO=null");
        }else if( accessTokenDTO.getAccessToken() == null){
            log.warn("微信接口调用凭据AccessToken刷新失败,errCode={},errMsg={}"
                    ,accessTokenDTO.getErrCode(),accessTokenDTO.getErrMsg());
        }else {
            log.info("微信接口调用凭据AccessToken刷新成功,accessToken={}"
                    ,accessTokenDTO.getAccessToken());
            stringRedisTemplate.opsForValue().set("wechat_server_access_token"
                    ,accessTokenDTO.getAccessToken(),accessTokenDTO.getExpiresIn()-60, TimeUnit.SECONDS);
        }
        return accessTokenDTO;
    }

    @Override
    @Nullable
    public String getAccessToken() {
        return stringRedisTemplate.opsForValue().get("wechat_server_access_token");
    }

    @Override
    public SendMessageDTO sendSubscribeMessage(String toUser, String templateId, Map<String, String> data) {
        return null;
    }

    @Override
    public SendMessageDTO sendFirstInterviewNotify(String toUser) {
        return null;
    }

    @Override
    public SendMessageDTO sendSecondInterviewNotify(String toUser) {
        return null;
    }

    @Override
    public SendMessageDTO sendWrittenTestNotify(String toUser) {
        return null;
    }
}
