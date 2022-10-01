package com.gdutelc.recruit.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;

/**
 * 对应微信小程序登录接口
 * @author TufSolareyes
 * @date 2022-08-05
 */
public interface ICode2Session {

    /**
     * 向微信获取用户登录信息
     * @param js_code 登录时由前端获取的code
     * @param grant_type 只需要填写 authorization_code
     * @return LoginInfo
     * @throws JsonProcessingException
     */
    ResultVO<LoginInfo> code2Session(String js_code, String grant_type) throws JsonProcessingException ;


}
