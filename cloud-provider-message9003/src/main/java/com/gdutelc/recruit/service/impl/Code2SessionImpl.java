package com.gdutelc.recruit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;
import com.gdutelc.recruit.service.interfaces.ICode2Session;
import com.gdutelc.recruit.utils.GenericUtils;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.gdutelc.recruit.constant.ResultStatusCodeConstant.SUCCESS;


/**
 * @author TUFSolareyes
 */
@Service
public class Code2SessionImpl implements ICode2Session {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${wx.applet.appid}")
    private String appid;

    @Value(value = "${wx.applet.secret}")
    private String secret;

    @Override
    public ResultVO<LoginInfo> code2Session(String js_code, String grant_type) throws JsonProcessingException {
        Map<String,String> params = new HashMap<>();
        params.put("appid",appid);
        params.put("secret",secret);
        params.put("js_code",js_code);
        params.put("grant_type",grant_type);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url = GenericUtils.splicingUrlStr(url,params);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if(entity.getStatusCodeValue() != SUCCESS){
            return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST,"微信服务器忙碌",null);
        }
        System.out.println(GenericUtils.getFullTimeStr() + " " + "请求了微信: " + url);
        String body = entity.getBody();
        System.out.println(GenericUtils.getFullTimeStr() + " " + "微信服务器返回: " + body);
        LoginInfo loginInfo = objectMapper.readValue(body, LoginInfo.class);
        if(loginInfo != null&&loginInfo.getOpenid() != null){
            stringRedisTemplate.opsForSet().add(RedisKeyConstant.STU_OPENID, loginInfo.getOpenid());
            return new ResultVO<>(SUCCESS,"登录成功",loginInfo);
        }else{
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"处理失败",null);
        }

    }
}
