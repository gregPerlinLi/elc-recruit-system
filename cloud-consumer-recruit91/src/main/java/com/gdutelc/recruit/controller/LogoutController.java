package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 面试官和管理员退出接口
 *
 * @author gregPerlinLi
 * @date 2022-08-23
 */
@RestController
@RequestMapping(value = "/interview/elc_access")
public class LogoutController {

    @Resource
    IInterviewService interviewService;

    /**
     * 面试官退出接口
     *
     * @param request Http 请求
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/interviewer_logout")
    @SentinelResource(value = "interviewerLogout", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "interviewerLogoutHandlerException")
    @ApiOperation(value = "面试官退出", tags = "logout", response = ResultVO.class)
    public ResultVO<Void> interviewerLogout(HttpServletRequest request) {
        String username = request.getSession().getAttribute("username").toString();
        request.getSession().removeAttribute("username");
        return interviewService.interviewerLogout(username);
    }

    /**
     * 管理员退出接口
     *
     * @param request Http 请求
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/admin_logout")
    @SentinelResource(value = "adminLogout", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "adminLogoutHandlerException")
    @ApiOperation(value = "管理员退出", tags = "logout", response = ResultVO.class)
    public ResultVO<Void> adminLogout(HttpServletRequest request) {
        String username = request.getSession().getAttribute("username").toString();
        request.getSession().removeAttribute("username");
        return interviewService.adminLogout(username);
    }

}
