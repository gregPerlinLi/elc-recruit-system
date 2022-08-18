package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.BriefAdjustInfoDTO;
import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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
    @SentinelResource(value = "detailedApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
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
    @SentinelResource(value = "briefApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "获取报名者简要信息集合", tags = "query", response = ResultVO.class)
    public ResultVO<PageDTO<BriefInfoDTO>> briefApplyQuery(@ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                           @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit,
                                                           @ApiParam(value = "部门筛选（0为全选）", required = true) @PathVariable("department") Integer department,
                                                           @ApiParam(value = "面试状态筛选（20为全选）", required = true) @PathVariable("stu_status_code") Integer stuStatusCode) {
        return interviewService.briefApplyQuery(page, limit, department, stuStatusCode);
    }

    /**
     * 获取调剂报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为该调剂报名者的简要信息集合
     */
    @GetMapping(value = "/brief_apply_query/{page}/{limit}")
    @SentinelResource(value = "briefAdjustApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "获取调剂报名者简要信息集合", tags = "query", response = ResultVO.class)
    public ResultVO<PageDTO<BriefAdjustInfoDTO>> briefAdjustApplyQuery(@ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                                       @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit) {
        return interviewService.briefAdjustApplyQuery(page, limit);
    }

    /**
     * 面试官评价接口
     *
     * @param comment 评价实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping(value = "/publish_comment")
    @SentinelResource(value = "publishComment", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "面试官发布评价", tags = "comment", response = ResultVO.class)
    public ResultVO addComment(@ApiParam(value = "评价信息", required = true) Comment comment) {
        return interviewService.addComment(comment);
    }

}
