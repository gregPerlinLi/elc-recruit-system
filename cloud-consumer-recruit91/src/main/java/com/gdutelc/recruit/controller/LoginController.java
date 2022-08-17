package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.service.interfaces.IMessageService;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@RestController
@RequestMapping(value = "/recruit/elc_access")
public class LoginController {

    @Resource
    private IInterviewService interviewService;

    @Resource
    private IMessageService messageService;

    /**
     * 学生登录接口（微信登录）
     *
     * @param jsCode 微信登录code
     * @param grantType 授权类型
     * @return {@link ResultVO}，其中数据为该学生姓名
     */
    @GetMapping(value = "/login/{js_code}/{grant_type}")
    @SentinelResource(value = "login", blockHandler = "loginHandlerException")
    @ApiOperation(value = "登录", tags = "login", response = ResultVO.class)
    public ResultVO<String> login(@ApiParam(value = "微信登录code", required = true) @PathVariable("js_code") String jsCode,
                                  @ApiParam(value = "授权类型", required = true) @PathVariable("grant_type") String grantType) {
        ResultVO<LoginInfo> messageResult = messageService.wxLogin(jsCode, grantType);
        if ( messageResult.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            String openid = messageResult.getData().getOpenid();
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "LOGIN SUCCESS", openid);
        }
        return new ResultVO<>(ResultStatusCodeConstant.SERVER_ERROR, "LOGIN FAILED");
    }

    /**
     * 面试官登录接口
     * @param username 用户名
     * @param password 密码（加密后的）
     * @param request HTTP 请求
     * @return {@link ResultVO}，其中数据为该面试官所在部门
     */
    @GetMapping(value = "/interviewer_login/{username}/{password}")
    @SentinelResource(value = "interviewerLogin", blockHandler = "interviewerLoginHandlerException")
    @ApiOperation(value = "面试官登录", tags = "login", response = ResultVO.class)
    public ResultVO<Integer> interviewerLogin(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username,
                                              @ApiParam(value = "密码（加密后的）", required = true) @PathVariable("password") String password,
                                              HttpServletRequest request) {
        ResultVO<Integer> result = interviewService.interviewerLogin(username, password, request.getSession().getId());
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            request.getSession().setAttribute("username", username);
        }
        return result;
    }

    /**
     * 管理员登录接口
     *
     * @param username 用户名
     * @param password 密码（加密后的）
     * @param request HTTP 请求
     * @return {@link ResultVO}，其中数据为该管理员的用户名
     */
    @GetMapping(value = "/admin_login/{username}/{password}")
    @SentinelResource(value = "adminLogin", blockHandler = "adminLoginHandlerException")
    @ApiOperation(value = "管理员登录", tags = "login", response = ResultVO.class)
    public ResultVO<String> adminLogin(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username,
                                       @ApiParam(value = "密码（加密后的）", required = true) @PathVariable("password") String password,
                                       HttpServletRequest request) {
        ResultVO<String> result = interviewService.adminLogin(username, password, request.getSession().getId());
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            request.getSession().setAttribute("username", username);
        }
        return result;
    }

    /**
     * Sentinel 异常处理 —— 学生登录接口
     */
    public ResultVO<Integer> loginHandlerException(@PathVariable("js_code") String jsCode,
                                                   @PathVariable("grant_type") String grantType,
                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 面试官登录接口
     */
    public ResultVO<String> interviewerLoginHandlerException(@PathVariable("username") String username,
                                                             @PathVariable("password") String password,
                                                             HttpServletRequest request,
                                                             BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 管理员登录接口
     */
    public ResultVO<String> adminLoginHandlerException(@PathVariable("username") String username,
                                                       @PathVariable("password") String password,
                                                       HttpServletRequest request,
                                                       BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

}
