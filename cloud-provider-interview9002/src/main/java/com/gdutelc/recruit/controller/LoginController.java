package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewerListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 面试官和管理员登录接口
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/pro/elc_public")
public class LoginController {

    @Resource
    IInterviewerListService interviewerListService;

    /**
     * 面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return 是否登录成功
     */
    @GetMapping(value = "/interviewer_login/{username}/{password}")
    public ResultVO<Integer> interviewerLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        return interviewerListService.login(username, password);
    }

    /**
     * 管理员登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return 是否登录成功
     */
    @GetMapping(value = "/admin_login/{username}/{password}")
    public ResultVO<Integer> adminLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        return null;
    }
}
