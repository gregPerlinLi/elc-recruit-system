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
    @GetMapping(value = "/get_now_process")
    @SentinelResource(value = "getNowProcess", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getNowProcessHandlerException")
    @ApiOperation(value = "获取当前面试进度", tags = "processStatus", response = ResultVO.class)
    public ResultVO<Integer> getNowProcess() {
        return interviewService.getNowProcess();
    }

}
