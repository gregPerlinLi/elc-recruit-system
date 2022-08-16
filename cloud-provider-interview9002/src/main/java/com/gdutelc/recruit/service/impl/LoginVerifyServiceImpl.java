package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.ILoginVerifyService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @date 2022-08-16
 */
public class LoginVerifyServiceImpl implements ILoginVerifyService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO<String> loginVerify(String username, String sessionId) {
        String loginSessionId = stringRedisTemplate.opsForValue().get(RedisKeyConstant.LOGIN_USER + username);
        if ( sessionId.equals(loginSessionId) ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "登录校验成功", loginSessionId);
        }
        return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN, "登录校验失败");
    }
}
