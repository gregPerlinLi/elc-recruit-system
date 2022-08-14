package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 学生登录服务
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean login(String openid) throws Exception {
        try {
            if ( Boolean.FALSE.equals(stringRedisTemplate.opsForSet().isMember("stu-openid", openid)) ) {
                stringRedisTemplate.opsForSet().add("stu-openid", openid);
            }
            return true;
        } catch ( Exception e ) {
            throw new Exception(e);
        }
    }
}
