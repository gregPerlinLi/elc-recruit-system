package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IAdminListService;
import com.gdutelc.recruit.service.interfaces.IInterviewerListService;
import com.gdutelc.recruit.service.interfaces.ILoginVerifyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 面试官和管理员登录接口
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/pro/interview/elc_public")
public class LoginController {

    @Resource
    IInterviewerListService interviewerListService;

    @Resource
    IAdminListService adminListService;

    @Resource
    ILoginVerifyService loginVerifyService;

    /**
     * 面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该面试官所在的部门
     */
    @GetMapping(value = "/interviewer_login/{username}/{password}/{session_id}")
    public ResultVO<String> interviewerLogin(@PathVariable("username") String username,
                                             @PathVariable("password") String password,
                                             @PathVariable("session_id") String sessionId) {
        return interviewerListService.login(username, password, sessionId);
    }

    /**
     * 管理员登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该管理员的用户名
     */
    @GetMapping(value = "/admin_login/{username}/{password}/{session_id}")
    public ResultVO<String> adminLogin(@PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("session_id") String sessionId) {
        return adminListService.login(username, password, sessionId);
    }

    /**
     * 面试官和管理员登录校验接口
     *
     * @param username 用户名
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为校验的Session ID
     */
    @GetMapping(value = "/login_verify/{username}/{session_id}")
    public ResultVO<String> loginVerify(@PathVariable("username") String username,
                                        @PathVariable("session_id") String sessionId) {
        return loginVerifyService.loginVerify(username, sessionId);
    }
}
