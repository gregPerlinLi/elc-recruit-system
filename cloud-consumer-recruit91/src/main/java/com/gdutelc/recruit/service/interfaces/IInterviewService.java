package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 面试模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Service
@FeignClient(name = "cloud-provider-interview", contextId = "InterviewServiceApi")
public interface IInterviewService {

    /**
     * 示例接口
     *
     * @param text 随便一个值，如果为空返回错误
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/demo/test/{text}")
    ResultVO<String> test(@PathVariable("text") String text);

    /**
     * 生产者面试官登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该面试官所在的部门
     */
    @GetMapping(value = "/pro/interview/elc_public/interviewer_login/{username}/{password}/{session_id}")
    ResultVO<Integer> interviewerLogin(@PathVariable("username") String username,
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
}
