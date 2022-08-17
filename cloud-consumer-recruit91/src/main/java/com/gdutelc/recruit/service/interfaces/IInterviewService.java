package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 面试模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Service
@FeignClient(name = "cloud-provider-interview", contextId = "InterviewServiceApi")
public interface IInterviewService {

    /**
     * 示例接口
     *
     * @param text 随便一个值，如果为空返回错误
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/demo/test/{text}")
    ResultVO<String> test(@PathVariable("text") String text);

    /**
     * 生产者面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该面试官所在的部门
     */
    @GetMapping(value = "/pro/interview/elc_public/interviewer_login/{username}/{password}/{session_id}")
    ResultVO<Integer> interviewerLogin(@PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("session_id") String sessionId);


    /**
     * 生产者面试官和管理员登录校验接口
     *
     * @param username 用户名
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为校验的Session ID
     */
    @GetMapping(value = "/pro/interview/elc_public/login_verify/{username}/{session_id}")
    ResultVO<String> loginVerify(@PathVariable("username") String username,
                                 @PathVariable("session_id") String sessionId);
}
