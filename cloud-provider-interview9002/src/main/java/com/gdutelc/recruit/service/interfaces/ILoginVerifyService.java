package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * 登录校验
 *
 * @author gregPerlinLi
 * @date 2022-08-16
 */
public interface ILoginVerifyService {

    /**
     * 登录校验服务
     *
     * @param username 要校验的用户名
     * @param sessionId 要校验的Session ID
     * @return {@link ResultVO}，其中数据为SessionID
     */
    ResultVO<String> loginVerify(String username, String sessionId);
}
