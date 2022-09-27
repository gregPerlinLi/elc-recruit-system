package com.gdutelc.recruit.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.gdutelc.recruit.domain.wx.AccessTokenDTO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;
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
    @Value("${wechat-server.sendMessageUrl}")
    private String sendMessageUrl;
    @Value("${wechat-server.miniProgramState}")
    private String miniProgramState;
    @Value("${wechat-server.interviewNotifyModelId}")
    private String interviewNotifyModelId;

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
        Map<String, Object> data = setNotifyData("电子科技协会", "实验-4 306",
                "2022-10-10 20:00", "电协招新第一次面试即日开始~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendSecondInterviewNotify(String toUser) {
        Map<String, Object> data = setNotifyData("电子科技协会", "实验-4 308",
                "2022-10-30 20:00", "电协招新第二次面试即日开始~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendFinallyPassedNotify(String toUser) {
        Map<String,Object> data = setNotifyData("电子科技协会","实验-4",
                "2022-10-31 20:00","恭喜你通过电子科技协会的笔试和面试！");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendWrittenTestNotify(String toUser) {
        Map<String, Object> data = setNotifyData("电子科技协会", "实验-4 208",
                "2022-10-20 20:00", "电协招新笔试即日开始~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendApplySuccessNotify(String toUser) {
        // TODO: 微信推送报名成功信息
        Map<String, Object> data = setNotifyData("电子科技协会", "实验4", GenericUtils.getFullTimeStr()
                , "电协招新报名成功~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendSignInSuccessNotify(String toUser) {
        // TODO: 这里是微信发送签到成功消息的调用
        Map<String, Object> data = setNotifyData("电子科技协会", "实验4", GenericUtils.getFullTimeStr()
                , "面试签到成功~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    @Override
    public SendMessageDTO sendInterviewStartNotify(String toUser) {
        // TODO: 微信推送开始面试消息
        Map<String, Object> data = setNotifyData("电子科技协会", "实验4", GenericUtils.getFullTimeStr(), "轮到您去面试啦~");
        return sendSubscribeMessage(toUser,interviewNotifyModelId,data);
    }

    private Map<String, Object> setNotifyData(String sender, String place, String time, String matter){
        Map<String, Object> data = new HashMap<>(6);
        Map<String, Object> name1 = new HashMap<>(2);
        Map<String, Object> thing4 = new HashMap<>(2);
        Map<String, Object> time13 = new HashMap<>(2);
        Map<String, Object> thing3 = new HashMap<>(2);
        name1.put("value",sender);
        thing4.put("value",place);
        time13.put("value",time);
        thing3.put("value",matter);
        data.put("name1",name1);
        data.put("thing4",thing4);
        data.put("time13",time13);
        data.put("thing3",thing3);
        return data;
    }
}
