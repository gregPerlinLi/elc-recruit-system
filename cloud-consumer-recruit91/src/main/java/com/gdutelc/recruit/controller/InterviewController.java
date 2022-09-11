package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.dto.*;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @SentinelResource(value = "detailedApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "detailedApplyQueryHandlerException")
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
    @SentinelResource(value = "briefApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "briefApplyQueryHandlerException")
    @ApiOperation(value = "获取报名者简要信息集合", tags = "query", response = ResultVO.class)
    public ResultVO<PageDTO<BriefInfoDTO>> briefApplyQuery(@ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                           @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit,
                                                           @ApiParam(value = "部门筛选（0为全选）", required = true) @PathVariable("department") Integer department,
                                                           @ApiParam(value = "面试状态筛选（20为全选）", required = true) @PathVariable("stu_status_code") Integer stuStatusCode) {
        return interviewService.briefApplyQuery(page, limit, department, stuStatusCode);
    }

    /**
     * 根据姓名<b>模糊</b>查询学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的报名者的简要信息集合
     */
    @GetMapping(value = "/search_stu_by_name/{name}/{page}/{limit}")
    @SentinelResource(value = "searchStuByName", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "searchStuByNameHandlerException")
    @ApiOperation(value = "根据姓名<b>模糊</b>查询学生集合接口", tags = "search", response = ResultVO.class)
    public ResultVO<PageDTO<BriefInfoDTO>> searchStuByName(@ApiParam(value = "模糊查询的名字", required = true) @PathVariable("name") String name,
                                                           @ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                           @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit) {
        return interviewService.searchStuByName(name, page, limit);
    }

    /**
     * 获取调剂报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为该调剂报名者的简要信息集合
     */
    @GetMapping(value = "/brief_adjust_apply_query/{page}/{limit}")
    @SentinelResource(value = "briefAdjustApplyQuery", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "briefAdjustApplyQueryHandlerException")
    @ApiOperation(value = "获取调剂报名者简要信息集合", tags = "query", response = ResultVO.class)
    public ResultVO<PageDTO<BriefAdjustInfoDTO>> briefAdjustApplyQuery(@ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                                       @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit) {
        return interviewService.briefAdjustApplyQuery(page, limit);
    }

    /**
     * 根据姓名<b>模糊</b>查询调剂学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的调剂报名者的简要信息集合
     */
    @GetMapping(value = "/search_adjust_stu_by_name/{name}/{page}/{limit}")
    @SentinelResource(value = "searchAdjustStuByName", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "searchAdjustStuByNameHandlerException")
    @ApiOperation(value = "根据姓名<b>模糊</b>查询调剂学生集合接口", tags = "search", response = ResultVO.class)
    public ResultVO<PageDTO<BriefInfoDTO>> searchAdjustStuByName(@ApiParam(value = "模糊查询的名字", required = true) @PathVariable("name") String name,
                                                                 @ApiParam(value = "页数", required = true) @PathVariable("page") Integer page,
                                                                 @ApiParam(value = "每页最大内容数", required = true) @PathVariable("limit") Integer limit) {
        return interviewService.searchAdjustStuByName(name, page, limit);
    }

    /**
     * 面试官评价接口
     *
     * @param commentDTO 评价实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping(value = "/publish_comment")
    @SentinelResource(value = "publishComment", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "addCommentHandlerException")
    @ApiOperation(value = "面试官发布评价", tags = "comment", response = ResultVO.class)
    public ResultVO<Void> addComment(@ApiParam(value = "评价信息", required = true) @RequestBody CommentDTO commentDTO) {
        return interviewService.addComment(commentDTO);
    }

    /**
     * 查询学生的所有评价
     *
     * @param stuId 需要查询的学生学号
     * @return {@link ResultVO}，其中数据为该报名者的评价集合
     */
    @GetMapping(value = "/query_comments/{stu_id}")
    @SentinelResource(value = "queryComments", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "queryCommentsHandlerException")
    @ApiOperation(value = "查询学生的所有评价", tags = "comment", response = ResultVO.class)
    public ResultVO<List<CommentDTO>> queryComments(@ApiParam(value = "需要查询的学生学号", required = true) @PathVariable("stu_id") String stuId) {
        return interviewService.queryComments(stuId);
    }

}
