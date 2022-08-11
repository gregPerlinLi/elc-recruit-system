package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.utils.ResultStatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@RestController("/elc_public")
public class LoginController {

    /**
     * 学生登录接口（微信登录）
     *
     * @param jsCode 微信登录code
     * @param grantType 授权类型
     * @return 数据库表的id
     */
    @GetMapping(value = "/login/{js_code}/{grant_type}")
    @SentinelResource(value = "login", blockHandler = "loginHandlerException")
    @ApiOperation(value = "登录", tags = "login", response = ResultVO.class)
    public ResultVO<Integer> login(@ApiParam(value = "微信登录code", required = true) @PathVariable("js_code") String jsCode,
                                  @ApiParam(value = "授权类型", required = true) @PathVariable("grant_type") String grantType) {
        return null;
    }

    /**
     * 面试官登录接口
     * @param username 用户名
     * @param password 密码（加密后的）
     * @return 是否成功登陆
     */
    @GetMapping(value = "/interviewer_login/{username}/{password}")
    @SentinelResource(value = "interviewer_login", blockHandler = "interviewerLoginHandlerException")
    @ApiOperation(value = "面试官登录", tags = "login", response = ResultVO.class)
    public ResultVO<String> interviewerLogin(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username,
                                             @ApiParam(value = "密码（加密后的）", required = true) @PathVariable("password") String password) {
        return null;
    }

    /**
     * 管理员登录接口
     *
     * @param username 用户名
     * @param password 密码（加密后的）
     * @return 是否成功登陆
     */
    @GetMapping(value = "/admin_login/{username}/{password}")
    @SentinelResource(value = "admin_login", blockHandler = "adminLoginHandlerException")
    @ApiOperation(value = "管理员登录", tags = "login", response = ResultVO.class)
    public ResultVO<String> adminLogin(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username,
                                       @ApiParam(value = "密码（加密后的）", required = true) @PathVariable("password") String password) {
        return null;
    }

    /**
     * Sentinel 异常处理 —— 学生登录接口
     */
    public ResultVO<Integer> loginHandlerException(@PathVariable("js_code") String jsCode,
                                                   @PathVariable("grant_type") String grantType,
                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCode.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 面试官登录接口
     */
    public ResultVO<String> interviewerLoginHandlerException(@PathVariable("username") String username,
                                                             @PathVariable("password") String password,
                                                             BlockException exception) {
        return new ResultVO<>(ResultStatusCode.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 管理员登录接口
     */
    public ResultVO<String> adminLoginHandlerException(@PathVariable("username") String username,
                                                       @PathVariable("password") String password,
                                                       BlockException exception) {
        return new ResultVO<>(ResultStatusCode.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

}
