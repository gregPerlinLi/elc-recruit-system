package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.ILoginService;
import com.gdutelc.recruit.utils.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 报名模块登录接口
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
@Slf4j
@RestController
@RequestMapping(value = "/pro/recruit/elc_public")
public class ApplyPublicController {

    @Resource
    private ILoginService loginService;

    /**
     * 生产者学生登录接口（将微信openid写入Redis中）
     * @param openid 微信opened
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     * @throws Exception 录入异常
     */
    @GetMapping("/login/{openid}")
    public ResultVO login(@PathVariable String openid) throws Exception {
        boolean result = loginService.login(openid);
        if ( result ) {
            return new ResultVO(ResultStatusCode.SUCCESS, "SUCCESS");
        } else {
            return new ResultVO(ResultStatusCode.SERVER_ERROR, "Failed to write openid into Redis");
        }
    }
}
