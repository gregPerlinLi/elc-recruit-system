package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 学生面试进度相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-25
 */
@RestController
@RequestMapping("/interview/elc_access/stu_status_code")
public class StudentStatusController {

    @Resource
    IInterviewService interviewService;

    /* 一二面开始面试接口 */

    /**
     * 一面开始面试接口
     *
     * @param stuId               开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/first_interview_start/{stu_id}/{interviewer_username}")
    @SentinelResource(value = "firstInterviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "一面开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> firstInterviewStart(@ApiParam(value = "开始面试的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                 @ApiParam(value = "面试官用户名", required = true) @PathVariable("interviewer_username") String interviewerUsername) {
        ResultVO<Integer> result = interviewService.firstInterviewStart(stuId, interviewerUsername);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            // TODO: 微信推送开始面试消息
        }
        return result;
    }

    /**
     * 二面开始面试接口
     *
     * @param stuId               开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_interview_start/{stu_id}/{interviewer_username}")
    @SentinelResource(value = "secondInterviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "二面开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewStart(@ApiParam(value = "开始面试学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                  @ApiParam(value = "面试官用户名", required = true) @PathVariable("interviewer_username") String interviewerUsername) {
        ResultVO<Integer> result = interviewService.secondInterviewStart(stuId, interviewerUsername);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            // TODO: 微信推送开始面试消息
        }
        return result;
    }

    /* 一二面面试通过接口 */

    /**
     * 一面通过接口
     *
     * @param stuId               通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/first_interview_pass/{stu_id}/{interviewer_username}")
    @SentinelResource(value = "firstInterviewPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "一面面试通过接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> firstInterviewPass(@ApiParam(value = "通过的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                @ApiParam(value = "面试官用户名", required = true) @PathVariable("interviewer_username") String interviewerUsername) {
        return interviewService.firstInterviewPass(stuId, interviewerUsername);
    }


    /**
     * 二面通过接口
     *
     * @param stuId               通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_interview_pass/{stu_id}/{interviewer_username}")
    @SentinelResource(value = "secondInterviewPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "二面面试通过接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewPass(@ApiParam(value = "通过的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                 @ApiParam(value = "面试官用户名", required = true) @PathVariable("interviewer_username") String interviewerUsername) {
        return interviewService.secondInterviewPass(stuId, interviewerUsername);
    }

    /* 调剂接口 */

    /**
     * 二面调剂接口
     *
     * @param stuId               调剂的学生学号
     * @param dept                学生想要调剂的部门
     * @param interviewerUsername 调剂的面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的第二志愿部门代码
     */
    @PutMapping(value = "/second_interview_adjust/{stu_id}/{dept}/{interviewer_username}")
    @SentinelResource(value = "secondInterviewAdjust", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "二面调剂接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewAdjust(@ApiParam(value = "调剂的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                   @ApiParam(value = "学生想要调剂的部门", required = true) @PathVariable("dept") Integer dept,
                                                   @ApiParam(value = "面试官用户名", required = true) @PathVariable("interviewer_username") String interviewerUsername) {
        return interviewService.secondInterviewAdjust(stuId, dept, interviewerUsername);
    }

    /**
     * 二面调剂开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_adjust_interview_start/{stu_id}")
    @SentinelResource(value = "secondAdjustInterviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "二面调剂开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondAdjustInterviewStart(@ApiParam(value = "开始面试的学生学号", required = true) @PathVariable("stu_id") String stuId) {
        ResultVO<Integer> result = interviewService.secondAdjustInterviewStart(stuId);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            // TODO: 微信推送开始面试消息
        }
        return result;
    }

    /**
     * 二面调剂通过接口
     *
     * @param stuId 通过的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_adjust_interview_pass/{stu_id}")
    @SentinelResource(value = "secondInterviewAdjustPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "flowLimitException")
    @ApiOperation(value = "二面调剂通过接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewAdjustPass(@ApiParam(value = "通过的学生学号", required = true) @PathVariable("stu_id") String stuId) {
        return interviewService.secondAdjustInterviewPass(stuId);
    }

}
