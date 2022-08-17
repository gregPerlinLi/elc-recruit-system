package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 面试相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/interview/elc_access")
public class InterviewController {

    @Resource
    IInterviewService interviewService;

    /**
     * 获取报名者详细信息接口
     *
     * @param stuId 需要查询的报名者学号
     * @return {@link ResultVO}，其中数据为该报名者的详细信息
     */
    @GetMapping(value = "/detailed_apply_query/{stu_id}")
    @SentinelResource(value = "studentQuery", blockHandler = "studentQueryBlockHandler")
    @ApiOperation(value = "获取报名者详细信息", tags = "query", response = ResultVO.class)
    public ResultVO<DetailedInfoDTO> detailedApplyQuery(@ApiParam(value = "报名者学号", readOnly = true) @PathVariable("stu_id") String stuId) {
        return interviewService.detailedApplyQuery(stuId);
    }


}
