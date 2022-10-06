package com.gdutelc.recruit.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.gdutelc.recruit.domain.wx.AccessTokenDTO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;
import com.gdutelc.recruit.enums.Usage;
import com.gdutelc.recruit.service.interfaces.ContentManageService;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import com.gdutelc.recruit.utils.GenericUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
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
    @Resource
    private ContentManageService contentManageService;
    @Value("${wechat-server.appid}")
    private String appId;
    @Value("${wechat-server.secret}")
    private String appSecret;
    @Value("${wechat-server.grantType}")
    private String grantType;
    @Value("${wechat-server.tokenUrl}")
    private String tokenUrl;
    @Value("${wechat-server.sendMessageUrl}")
    private String sendMessageUrl;
    @Value("${wechat-server.miniProgramState}")
    private String miniProgramState;

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
    public String getAccessToken() {
        return stringRedisTemplate.opsForValue().get("wechat_server_access_token");
    }

    @Override
    public SendMessageDTO sendSubscribeMessage(String toUser, String templateId, Map<String, Object> data) {
        String accessToken = getAccessToken();
        String url = sendMessageUrl + "?access_token=" + accessToken;
        Map<String,Object> uriVariable = new HashMap<>(16);
        uriVariable.put("access_token",accessToken);
        uriVariable.put("touser",toUser);
        uriVariable.put("data", data);
        uriVariable.put("template_id",templateId);
        uriVariable.put("miniprogram_state",miniProgramState);
        uriVariable.put("lang","zh_CN");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept","application/json");
        HttpEntity<Object> httpEntity = new HttpEntity<>(JSONUtils.toJSONString(uriVariable),headers);
        SendMessageDTO sendMessageDTO = restTemplate.postForObject(url, httpEntity, SendMessageDTO.class);

        if(sendMessageDTO == null){
            log.error("微信消息通知接口调用失败,sendMessageDTO=null");
        }else if( sendMessageDTO.getErrCode() != 0){
            log.warn("微信消息通知接口调用失败,errCode={},errMsg={}"
                    ,sendMessageDTO.getErrCode(),sendMessageDTO.getErrMsg());
        }else {
            log.warn("微信消息通知接口调用成功，receiver openid={}",toUser);
        }
        return sendMessageDTO;
    }

    @Override
    public SendMessageDTO sendFirstInterviewNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.FIRST_INTERVIEW);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.FIRST_INTERVIEW);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendSecondInterviewNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.SECOND_INTERVIEW);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.SECOND_INTERVIEW);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendFinallyPassedNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.FINALLY_PASSED);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.FINALLY_PASSED);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendWrittenTestNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.WRITTEN_TEST);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.WRITTEN_TEST);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendApplySuccessNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.APPLY_SUCCESS);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.APPLY_SUCCESS);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendSignInSuccessNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.SIGN_IN_SUCCESS);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.SIGN_IN_SUCCESS);
        return sendSubscribeMessage(toUser,modelId,data);
    }

    @Override
    public SendMessageDTO sendInterviewStartNotify(String toUser) {
        String modelId = contentManageService.getNotifyModelId(Usage.INTERVIEW_START);
        Map<String, Object> data = contentManageService.getPackedNotifyData(Usage.INTERVIEW_START);
        return sendSubscribeMessage(toUser,modelId,data);
    }

}
