package com.gdutelc.recruit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.entities.wx.LoginInfo;

/**
 * 对应微信小程序登录接口
 * @author TufSolareyes
 * @date 2022-08-05
 */
public interface Code2Session_Wx {

    /**
     * 向微信获取用户登录信息
     * @param appid 小程序appid
     * @param secret 小程序appSecret
     * @param js_code 登录时由前端获取的code
     * @param grant_type 只需要填写 authorization_code
     * @return LoginInfo
     * @throws JsonProcessingException
     */
    LoginInfo code2Session(String appid, String secret, String js_code, String grant_type) throws JsonProcessingException ;
}
