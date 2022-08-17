package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
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
    @SentinelResource(value = "detailedApplyQuery", blockHandler = "detailedApplyQueryHandlerException")
    @ApiOperation(value = "获取报名者详细信息", tags = "query", response = ResultVO.class)
    public ResultVO<DetailedInfoDTO> detailedApplyQuery(@ApiParam(value = "报名者学号", required = true) @PathVariable("stu_id") String stuId) {
        return interviewService.detailedApplyQuery(stuId);
    }

    /**
     * 获取报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @param department 部门筛选（0为全选）
     * @param stuStatusCode 面试状态筛选（20为全选）
     * @return {@link ResultVO}，其中数据为该报名者的简要信息集合
     */
    @GetMapping(value = "/brief_apply_query/{page}/{limit}/{department}/{stu_status_code}")
    @SentinelResource(value = "briefApplyQuery", blockHandler = "briefApplyQueryHandlerException")
    @ApiOperation(value = "获取报名者简要信息集合", tags = "query", response = ResultVO.class)
    public ResultVO<PageDTO<BriefInfoDTO>> briefApplyQuery(@ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                           @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit,
                                                           @ApiParam(value = "部门筛选（0为全选）", required = true) @PathVariable("department") Integer department,
                                                           @ApiParam(value = "面试状态筛选（20为全选）", required = true) @PathVariable("stu_status_code") Integer stuStatusCode) {
        return interviewService.briefApplyQuery(page, limit, department, stuStatusCode);
    }

    /**
     * Sentinel 异常处理 —— 获取报名者详细信息接口
     */
    public ResultVO<DetailedInfoDTO> detailedApplyQueryHandlerException(@PathVariable("stu_id") String stuId,
                                                                        BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 获取报名者简要信息集合接口
     */
    public ResultVO<PageDTO<BriefInfoDTO>> briefApplyQueryHandlerException(@PathVariable("page") Integer page,
                                                                           @PathVariable("limit") Integer limit,
                                                                           @PathVariable("department") Integer department,
                                                                           @PathVariable("stu_status_code") Integer stuStatusCode,
                                                                           BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

}
