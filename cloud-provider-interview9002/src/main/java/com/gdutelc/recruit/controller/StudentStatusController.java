package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IAdjustStuInfoService;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 学生状态码相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-25
 */
@RestController
@RequestMapping("/pro/interview/elc_access/stu_status_code")
public class StudentStatusController {

    @Resource
    IStuInfoService stuInfoService;

    @Resource
    IAdjustStuInfoService adjustStuInfoService;

    /* 一二面开始面试接口 */

    /**
     * 开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/interview_start/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> interviewStart(@PathVariable("stu_id") String stuId,
                                            @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = stuInfoService.interviewStart(stuId, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "学生第一志愿部门和面试官所在部门不一致");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生或面试官");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /**
     * 二面开始面试接口
     *
     * @deprecated 请使用统一的面试开始接口 {@link #interviewStart(String, String)}
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = "/second_interview_start/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewStart(@PathVariable("stu_id") String stuId,
                                                  @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = stuInfoService.interviewStart(stuId, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "学生第一志愿部门和面试官所在部门不一致");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生或面试官");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /* 一二面面试通过接口 */

    /**
     * 面试通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/interview_pass/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> interviewPass(@PathVariable("stu_id") String stuId,
                                           @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = stuInfoService.interviewPass(stuId, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "学生第一志愿部门和面试官所在部门不一致");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生或面试官");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /**
     * 二面通过接口
     *
     * @deprecated 请使用统一的面试通过接口 {@link #interviewPass(String, String)}
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = "/second_interview_pass/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewPass(@PathVariable("stu_id") String stuId,
                                                 @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = stuInfoService.interviewPass(stuId, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "学生第一志愿部门和面试官所在部门不一致");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生或面试官");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /* 调剂接口 */

    /**
     * 二面调剂接口
     *
     * @param stuId 调剂的学生学号
     * @param dept 学生想要调剂的部门
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的第二志愿部门代码
     */
    @PutMapping(value = "/second_interview_adjust/{stu_id}/{dept}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewAdjust(@PathVariable("stu_id") String stuId,
                                                   @PathVariable("dept") Integer dept,
                                                   @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = adjustStuInfoService.adjust(stuId, dept, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "学生第一志愿部门和面试官所在部门不一致");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生或面试官");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /**
     * 二面调剂开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_adjust_interview_start/{stu_id}")
    public ResultVO<Integer> secondAdjustInterviewStart(@PathVariable("stu_id") String stuId) {
        Integer result = adjustStuInfoService.interviewStart(stuId);
        if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }

    /**
     * 二面调剂通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_adjust_interview_pass/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewAdjustPass(@PathVariable("stu_id") String stuId, @PathVariable("interviewer_username") String interviewerUsername) {
        Integer result = adjustStuInfoService.interviewPass(stuId, interviewerUsername);
        if ( result == ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "该学生已被录取");
        } else if ( result == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "不存在此学生");
        } else if ( result == ResultStatusCodeConstant.FAILED ) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "由于学生状态不符合要求，请求失败");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "设置成功", result);
        }
    }
}
