package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IAdminListService;
import com.gdutelc.recruit.service.interfaces.IInterviewerListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 面试官和管理员退出接口
 *
 * @author gregPerlinLi
 * @date 2022-08-23
 */
@RestController
@RequestMapping("/pro/interview/elc_access")
public class LogoutController {

    @Resource
    IInterviewerListService interviewerListService;

    @Resource
    IAdminListService adminListService;

    /**
     * 面试官退出接口
     *
     * @param sessionId 请求SessionID
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/interviewer_logout/{session_id}")
    public ResultVO<Void> interviewerLogout(@PathVariable("session_id") String sessionId) {
        return interviewerListService.logout(sessionId);
    }

    /**
     * 管理员退出接口
     *
     * @param sessionId 请求SessionID
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/admin_logout/{session_id}")
    public ResultVO<Void> adminLogout(@PathVariable("session_id") String sessionId) {
        return adminListService.logout(sessionId);
    }
}
