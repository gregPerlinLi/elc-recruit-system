package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.service.interfaces.IMessageService;
import com.gdutelc.recruit.utils.GenericUtils;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 学生面试进度相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-25
 */
@Slf4j
@RestController
@RequestMapping("/interview/elc_access/stu_status_code")
public class StudentStatusController {

    @Resource
    IInterviewService interviewService;

    @Resource
    IMessageService messageService;

    /* 一二面开始面试接口 */

    /**
     * 面试开始面试接口
     *
     * @param stuId               开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = {"/interview_start/{stu_id}/{interviewer_username}",
                         "/interview_start/{stu_id}"})
    @SentinelResource(value = "interviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "interviewStartHandlerException")
    @ApiOperation(value = "面试开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> interviewStart(@ApiParam(value = "开始面试的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                            @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                            HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        ResultVO<Integer> result = interviewService.interviewStart(stuId, interviewerUsername);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            ResultVO<Void> sendMessageResult = messageService.interviewStartNotify(stuId);
            if ( sendMessageResult.getCode() != ResultStatusCodeConstant.SUCCESS ) {
                log.warn("面试开始面试接口，微信推送开始面试消息失败，学号: {}, 失败原因: {}", stuId, sendMessageResult.getMsg());
                return new ResultVO<>(ResultStatusCodeConstant.FAILED, "面试开始面试接口成功，但是微信推送消息失败");
            }
        }
        return result;
    }

    /**
     * 二面开始面试接口
     *
     * @deprecated                二面开始面试接口已废弃，二面开始面试接口与一面开始面试接口合并，请使用 {@link #interviewStart(String, String, HttpServletRequest)} 接口
     * @param stuId               开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = {"/second_interview_start/{stu_id}/{interviewer_username}",
                         "/second_interview_start/{stu_id}"})
    @SentinelResource(value = "secondInterviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewStartHandlerException")
    @ApiOperation(value = "二面开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewStart(@ApiParam(value = "开始面试学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                  @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                  HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        ResultVO<Integer> result = interviewService.secondInterviewStart(stuId, interviewerUsername);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            ResultVO<Void> sendMessageResult = messageService.interviewStartNotify(stuId);
            if ( sendMessageResult.getCode() != ResultStatusCodeConstant.SUCCESS ) {
                log.warn("面试开始面试接口，微信推送开始面试消息失败，学号: {}, 失败原因: {}", stuId, sendMessageResult.getMsg());
                return new ResultVO<>(ResultStatusCodeConstant.FAILED, "面试开始面试接口成功，但是微信推送消息失败");
            }
        }
        return result;
    }

    /* 一二面面试通过接口 */

    /**
     * 面试通过接口
     *
     * @param stuId               通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = {"/interview_pass/{stu_id}/{interviewer_username}",
                         "/interview_pass/{stu_id}"})
    @SentinelResource(value = "interviewPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "interviewPassHandlerException")
    @ApiOperation(value = "一面面试通过接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> interviewPass(@ApiParam(value = "通过的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                           @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                           HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        return interviewService.interviewPass(stuId, interviewerUsername);
    }


    /**
     * 二面通过接口
     *
     * @deprecated                二面通过接口已废弃，二面通过接口与一面通过接口合并，请使用 {@link #interviewPass(String, String, HttpServletRequest)} 接口
     * @param stuId               通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = "/second_interview_pass/{stu_id}/{interviewer_username}")
    @SentinelResource(value = "secondInterviewPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewPassHandlerException")
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
     * @param department          学生想要调剂的部门
     * @param interviewerUsername 调剂的面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的第二志愿部门代码
     */
    @PutMapping(value = {"/second_interview_adjust/{stu_id}/{department}/{interviewer_username}",
                         "/second_interview_adjust/{stu_id}/{department}"})
    @SentinelResource(value = "secondInterviewAdjust", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewAdjustHandlerException")
    @ApiOperation(value = "二面调剂接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewAdjust(@ApiParam(value = "调剂的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                   @ApiParam(value = "学生想要调剂的部门", required = true) @PathVariable("department") Integer department,
                                                   @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                   HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        return interviewService.secondInterviewAdjust(stuId, department, interviewerUsername);
    }

    /**
     * 二面调剂开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = {"/second_adjust_interview_start/{stu_id}/{interviewer_username}",
                         "/second_adjust_interview_start/{stu_id}"})
    @SentinelResource(value = "secondAdjustInterviewStart", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondAdjustInterviewStartHandlerException")
    @ApiOperation(value = "二面调剂开始面试接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondAdjustInterviewStart(@ApiParam(value = "开始面试的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                        @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                        HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        ResultVO<Integer> result = interviewService.secondAdjustInterviewStart(stuId, interviewerUsername);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            ResultVO<Void> sendMessageResult = messageService.interviewStartNotify(stuId);
            if ( sendMessageResult.getCode() != ResultStatusCodeConstant.SUCCESS ) {
                log.warn("面试开始面试接口，微信推送开始面试消息失败，学号: {}, 失败原因: {}", stuId, sendMessageResult.getMsg());
                return new ResultVO<>(ResultStatusCodeConstant.FAILED, "面试开始面试接口成功，但是微信推送消息失败");
            }
        }
        return result;
    }

    /**
     * 二面调剂通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = {"/second_adjust_interview_pass/{stu_id}/{interviewer_username}",
                         "/second_adjust_interview_pass/{stu_id}"})
    @SentinelResource(value = "secondInterviewAdjustPass", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondAdjustInterviewPassHandlerException")
    @ApiOperation(value = "二面调剂通过接口", tags = "studentStatus", response = ResultVO.class)
    public ResultVO<Integer> secondInterviewAdjustPass(@ApiParam(value = "通过的学生学号", required = true) @PathVariable("stu_id") String stuId,
                                                       @ApiParam(value = "面试官用户名", required = false) @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                       HttpServletRequest request) {
        if ( !GenericUtils.ofNullable(interviewerUsername) ) {
            interviewerUsername = request.getSession().getAttribute("username").toString();
        }
        return interviewService.secondAdjustInterviewPass(stuId, interviewerUsername);
    }

}
