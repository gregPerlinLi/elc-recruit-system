package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.ILoginVerifyService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @date 2022-08-16
 */
@Service
public class LoginVerifyServiceImpl implements ILoginVerifyService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO<String> loginVerify(String username, String sessionId) {
        return loginVerify2(username,sessionId);
//        String loginSessionId = stringRedisTemplate.opsForValue().get(RedisKeyConstant.LOGIN_USER + username);
//        if ( sessionId.equals(loginSessionId) ) {
//            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "登录校验成功", loginSessionId);
//        }
//        return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN, "登录校验失败");
    }


    public ResultVO<String> loginVerify2(String username,String sessionId) {
        Boolean aBoolean = stringRedisTemplate.hasKey(sessionId);
        String ausername = stringRedisTemplate.opsForValue().get(sessionId);
        if(aBoolean != null && aBoolean && ausername != null && ausername.equals(username)) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "登录校验成功", sessionId);
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "登录校验失败");
        }
    }
}
