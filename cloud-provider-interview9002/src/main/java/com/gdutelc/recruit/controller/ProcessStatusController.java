package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IProcessService;
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
@RequestMapping("/pro/interview/elc_access/process_status_code")
public class ProcessStatusController {

    @Resource
    IProcessService processService;

    /**
     * 获取当前面试总进度
     *
     * @return {@link ResultVO}，其中数据为当前面试总进度
     */
    @GetMapping(value = "/get_now_process")
    public ResultVO<Integer> getNowProcess() {
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "获取成功", processService.getNowProcess());
    }
}
