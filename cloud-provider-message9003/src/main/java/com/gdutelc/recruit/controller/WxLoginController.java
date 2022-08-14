package com.gdutelc.recruit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;
import com.gdutelc.recruit.service.interfaces.ICode2Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微信登录接口
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
@RestController
@RequestMapping("/pro/elc_public")
public class WxLoginController {

    @Resource
    ICode2Session code2Session;

    /**
     * 微信登录接口
     *
     * @param jsCode 微信登录code
     * @param grantType 授权类型
     * @return {@link ResultVO}，其中数据为小程序登录信息
     * @throws JsonProcessingException Json处理异常
     */
    @GetMapping(value = "/wx_login/{js_code}/{grant_type}")
    public ResultVO<LoginInfo> wxLogin(@PathVariable("js_code") String jsCode, @PathVariable("grant_type") String grantType) throws JsonProcessingException {
        return code2Session.code2Session(jsCode, grantType);
    }
}
