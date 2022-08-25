package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 面试总进度相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-25
 */
@RestController
@RequestMapping("/interview/elc_access/process_status_code")
public class ProcessStatusController {
    @Resource
    IInterviewService interviewService;

    /**
     * 获取当前面试总进度
     *
     * @return {@link ResultVO}，其中数据为当前面试总进度
     */
    @RequestMapping(value = "/get_now_process")
    public ResultVO<Integer> getNowProcess() {
        return interviewService.getNowProcess();
    }

}
