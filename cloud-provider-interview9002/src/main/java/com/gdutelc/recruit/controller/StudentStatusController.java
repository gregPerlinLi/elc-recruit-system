package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生状态码相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-25
 */
@RestController
@RequestMapping("/pro/interview/elc_access/stu_status_code")
public class StudentStatusController {

    /**
     * 一面开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/first_interview_start/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> firstInterviewStart(@PathVariable("stu_id") String stuId,
                                                 @PathVariable("interviewer_username") String interviewerUsername) {
        return null;
    }

    /**
     * 二面开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_interview_start/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewStart(@PathVariable("stu_id") String stuId,
                                                  @PathVariable("interviewer_username") String interviewerUsername) {
        return null;
    }

    /**
     * 二面调剂开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_adjust_interview_start/{stu_id}")
    public ResultVO<Integer> secondAdjustInterviewStart(@PathVariable("stu_id") String stuId) {
        return null;
    }

    /**
     * 一面通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/first_interview_pass/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> firstInterviewPass(@PathVariable("stu_id") String stuId,
                                                @PathVariable("interviewer_username") String interviewerUsername) {
        return null;
    }

    /**
     * 二面通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_interview_pass/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewPass(@PathVariable("stu_id") String stuId,
                                                 @PathVariable("interviewer_username") String interviewerUsername) {
        return null;
    }

    /**
     * 二面调剂接口
     *
     * @param stuId 调剂的学生学号
     * @param interviewerUsername 调剂的面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的第二志愿部门代码
     */
    @PutMapping(value = "/second_interview_adjust/{stu_id}/{interviewer_username}")
    public ResultVO<Integer> secondInterviewAdjust(@PathVariable("stu_id") String stuId,
                                                   @PathVariable("interviewer_username") String interviewerUsername) {
        return null;
    }

    /**
     * 二面调剂通过接口
     *
     * @param stuId 通过的学生学号
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/second_interview_adjust_pass/{stu_id}")
    public ResultVO<Integer> secondInterviewAdjustPass(@PathVariable("stu_id") String stuId) {
        return null;
    }
}
