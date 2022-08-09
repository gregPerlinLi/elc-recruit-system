package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 面试官和管理员登录接口
 *
 * @author gregPerlinLi
 * @date 2022-08-09
 */
@RestController("/pro/elc_public")
public class LoginController {

    /**
     * 面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return 是否登录成功
     */
    @GetMapping(value = "/interviewer_login/{username}/{password}")
    @ResponseBody
    public ResultVO<String> interviewerLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        return null;
    }

    /**
     * 管理员登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return 是否登录成功
     */
    @GetMapping(value = "/admin_login/{username}/{password}")
    @ResponseBody
    public ResultVO<String> adminLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        return null;
    }
}
