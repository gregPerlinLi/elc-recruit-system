package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.dto.*;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * 面试模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Service
@FeignClient(name = "cloud-provider-interview", contextId = "InterviewServiceApi")
public interface IInterviewService {

    /* 以下为示例接口 */

    /**
     * 示例接口
     *
     * @param text 随便一个值，如果为空返回错误
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/demo/test/{text}")
    ResultVO<String> test(@PathVariable("text") String text);

    /* 以下为登录相关接口 */

    /**
     * 生产者面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该面试官所在的部门
     */
    @GetMapping(value = "/pro/interview/elc_public/interviewer_login/{username}/{password}/{session_id}")
    ResultVO<String> interviewerLogin(@PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("session_id") String sessionId);

    /**
     * 生产者管理员登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该管理员的用户名
     */
    @GetMapping(value = "/pro/interview/elc_public/admin_login/{username}/{password}/{session_id}")
    ResultVO<String> adminLogin(@PathVariable("username") String username,
                                @PathVariable("password") String password,
                                @PathVariable("session_id") String sessionId);

    /**
     * 生产者面试官注销登录接口
     *
     * @param sessionId 请求SessionID
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/interview/elc_access/interviewer_logout/{session_id}")
    ResultVO<Void> interviewerLogout(@PathVariable("session_id") String sessionId);

    /**
     * 生产者管理员注销登录接口
     *
     * @param sessionId 请求SessionID
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/interview/elc_access/admin_logout/{session_id}")
    ResultVO<Void> adminLogout(@PathVariable("session_id") String sessionId);

    /*  以下为学生查询相关接口 */

    /**
     * 生产者面试官和管理员登录校验接口
     *
     * @param username 用户名
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为校验的Session ID
     */
    @GetMapping(value = "/pro/interview/elc_public/login_verify/{username}/{session_id}")
    ResultVO<String> loginVerify(@PathVariable("username") String username,
                                 @PathVariable("session_id") String sessionId);

    /**
     * 生产者获取报名者详细信息接口
     *
     * @param stuId 需要查询的报名者学号
     * @return {@link ResultVO}，其中数据为该报名者的详细信息
     */
    @GetMapping(value = "/pro/interview/elc_access/detailed_apply_query/{stu_id}")
    ResultVO<DetailedInfoDTO> detailedApplyQuery(@PathVariable("stu_id") String stuId);


    /**
     * 生产者获取报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @param department 部门筛选（0为全选）
     * @param stuStatusCode 面试状态筛选（20为全选）
     * @return {@link ResultVO}，其中数据为该报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/brief_apply_query/{page}/{limit}/{department}/{stu_status_code}")
    ResultVO<PageDTO<BriefInfoDTO>> briefApplyQuery(@PathVariable("page") Integer page,
                                                    @PathVariable("limit") Integer limit,
                                                    @PathVariable("department") Integer department,
                                                    @PathVariable("stu_status_code") Integer stuStatusCode);

    /**
     * 生产者根据姓名<b>模糊</b>查询学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/search_stu_by_name/{name}/{page}/{limit}")
    ResultVO<PageDTO<BriefInfoDTO>> searchStuByName(@PathVariable("name") String name,
                                                    @PathVariable("page") Integer page,
                                                    @PathVariable("limit") Integer limit);

    /**
     * 生产者根据学号<b>模糊</b>查询学生集合接口
     *
     * @param stuId 模糊查询的学号
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/search_stu_by_stu_id/{stu_id}/{page}/{limit}")
    ResultVO<PageDTO<BriefInfoDTO>> searchStuByStuId(@PathVariable("stu_id") String stuId,
                                                     @PathVariable("page") Integer page,
                                                     @PathVariable("limit") Integer limit);

    /**
     * 生产者获取调剂报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为该调剂报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/brief_adjust_apply_query/{page}/{limit}")
    ResultVO<PageDTO<BriefAdjustInfoDTO>> briefAdjustApplyQuery(@PathVariable("page") Integer page,
                                                                @PathVariable("limit") Integer limit);

    /**
     * 生产者根据姓名<b>模糊</b>查询调剂学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的调剂报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/search_adjust_stu_by_name/{name}/{page}/{limit}")
    ResultVO<PageDTO<BriefAdjustInfoDTO>> searchAdjustStuByName(@PathVariable("name") String name,
                                                                @PathVariable("page") Integer page,
                                                                @PathVariable("limit") Integer limit);

    /**
     * 生产者根据学号<b>模糊</b>查询调剂学生集合接口
     *
     * @param stuId 模糊查询的学号
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的调剂报名者的简要信息集合
     */
    @GetMapping(value = "/pro/interview/elc_access/search_adjust_stu_by_stu_id/{stu_id}/{page}/{limit}")
    ResultVO<PageDTO<BriefAdjustInfoDTO>> searchAdjustStuByStuId(@PathVariable("stu_id") String stuId,
                                                                 @PathVariable("page") Integer page,
                                                                 @PathVariable("limit") Integer limit);

    /* 以下为评价相关接口 */

    /**
     * 生产者面试官评价接口
     *
     * @param commentDTO 评价实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping(value = "/pro/interview/elc_access/publish_comment")
    ResultVO<Void> addComment(CommentDTO commentDTO);

    /**
     * 生产者查询学生的所有评价
     *
     * @param stuId 需要查询的学生学号
     * @return {@link ResultVO}，其中数据为该报名者的评价集合
     */
    @GetMapping(value = "/pro/interview/elc_access/query_comments/{stu_id}")
    ResultVO<List<CommentDTO>> queryComments(@PathVariable("stu_id") String stuId);

    /* 以下为面试进度相关接口 */

    /**
     * 生产者面试开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/interview_start/{stu_id}/{interviewer_username}")
    ResultVO<Integer> interviewStart(@PathVariable("stu_id") String stuId,
                                     @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 生产者二面开始面试接口
     *
     * @deprecated 该接口已废弃，二面开始面试接口与一面开始面试接口合并，请使用 {@link #interviewStart(String, String)} 接口
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/second_interview_start/{stu_id}/{interviewer_username}")
    ResultVO<Integer> secondInterviewStart(@PathVariable("stu_id") String stuId,
                                           @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 生产者面试通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/interview_pass/{stu_id}/{interviewer_username}")
    ResultVO<Integer> interviewPass(@PathVariable("stu_id") String stuId,
                                    @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 生产者二面通过接口
     *
     * @deprecated 该接口已废弃，二面通过接口与一面通过接口合并，请使用 {@link #interviewPass(String, String)} 接口
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @Deprecated
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/second_interview_pass/{stu_id}/{interviewer_username}")
    ResultVO<Integer> secondInterviewPass(@PathVariable("stu_id") String stuId,
                                          @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 二面调剂接口
     *
     * @param stuId 调剂的学生学号
     * @param department 学生想要调剂的部门
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的第二志愿部门代码
     */
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/second_interview_adjust/{stu_id}/{department}/{interviewer_username}")
    ResultVO<Integer> secondInterviewAdjust(@PathVariable("stu_id") String stuId,
                                            @PathVariable("department") Integer department,
                                            @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 二面调剂开始面试接口
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/second_adjust_interview_start/{stu_id}/{interviewer_username}")
    ResultVO<Integer> secondAdjustInterviewStart(@PathVariable("stu_id") String stuId,
                                                 @PathVariable("interviewer_username") String interviewerUsername);

    /**
     * 二面调剂通过接口
     *
     * @param stuId 通过的学生学号
     * @param interviewerUsername 面试官用户名
     * @return {@link ResultVO}，其中数据为当前学生的状态码
     */
    @PutMapping(value = "/pro/interview/elc_access/stu_status_code/second_adjust_interview_pass/{stu_id}/{interviewer_username}")
    ResultVO<Integer> secondAdjustInterviewPass(@PathVariable("stu_id") String stuId, @PathVariable("interviewer_username") String interviewerUsername);

    /* 以下为面试总进度相关接口 */

    /**
     * 生产者获取当前面试总进度接口
     *
     * @return {@link ResultVO}，其中数据为当前面试总进度
     */
    @GetMapping(value = "/pro/interview/elc_access/process_status_code/get_now_process")
    ResultVO<Integer> getNowProcess();

    /**
     * 生产者获取当前以前到的学生集合接口
     *
     * @param deptId 筛选的部门代码
     * @return {@link ResultVO}，其中数据为已签到的学生集合
     */
    @GetMapping(value = "/pro/interview/elc_access/getSignInList/{deptId}")
    ResultVO<List<SignInDTO>> getSignInList(@PathVariable("deptId") Integer deptId);

    /**
     * 生产者获取各个部门的人数情况接口
     *
     * @return {@link ResultVO}，其中数据为当前各部门人数情况集合
     */
    @GetMapping(value = "/pro/interview/elc_access/getDeptPeopleCount")
    ResultVO<List<Integer>> getDeptPeopleCount();

    /**
     * 生产者获取学生状态统计接口
     *
     * @return {@link ResultVO}，其中数据为当前学生状态统计
     */
    @GetMapping(value = "/pro/interview/elc_access/get_status_people_count")
    ResultVO<List<Long>> getStatusPeopleCount();

}
