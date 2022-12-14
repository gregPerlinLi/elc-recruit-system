package com.gdutelc.recruit.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.*;
import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Sentinel限流处理方法
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
public class SentinelBlockHandler {

    /* DemoController */

    public static ResultVO<String> handlerException(@PathVariable("text") String text,
                                                    BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* LoginController */

    /**
     * Sentinel 异常处理 —— 学生登录接口
     */
    public static ResultVO<Integer> loginHandlerException(@PathVariable("js_code") String jsCode,
                                                          @PathVariable("grant_type") String grantType,
                                                          BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 面试官登录接口
     */
    public static ResultVO<String> interviewerLoginHandlerException(@PathVariable("username") String username,
                                                                    @PathVariable("password") String password,
                                                                    HttpServletRequest request,
                                                                    BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 管理员登录接口
     */
    public static ResultVO<String> adminLoginHandlerException(@PathVariable("username") String username,
                                                              @PathVariable("password") String password,
                                                              HttpServletRequest request,
                                                              BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* LogoutController */

    /**
     * Sentinel 异常处理 —— 面试官退出接口
     */
    public static ResultVO<Void> interviewerLogoutHandlerException(HttpServletRequest request,
                                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理 —— 管理员退出接口
     */
    public static ResultVO<Void> adminLogoutHandlerException(HttpServletRequest request,
                                                             BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* ApplyController */

    /**
     * Sentinel 异常处理——报名接口
     */
    public static ResultVO<String> applyHandlerException (ApplyInfoDTO applyInfoDTO,
                                                          BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取个人完整报名信息接口
     */
    public static ResultVO<ApplyInfoDTO> getApplyInfoHandlerException (@PathVariable("openid") String openid,
                                                                       BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取当前学生面试状态接口
     */
    public static ResultVO<Integer> getStatusHandlerException(@PathVariable("openid") String openid,
                                                              BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——修改学生面试信息接口
     */
    public static ResultVO<String> updateApplyInfoHandlerException(ApplyInfoDTO applyInfoDTO,
                                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——学生签到接口
     */
    public static ResultVO<Integer> signInHandlerException(@PathVariable("openid") String openid,
                                                           @PathVariable("key") String key,
                                                           BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* InterviewController */

    /**
     * Sentinel 异常处理——获取报名者详细信息接口
     */
    public static ResultVO<DetailedInfoDTO> detailedApplyQueryHandlerException(@PathVariable("stu_id") String stuId,
                                                                               BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取报名者简要信息集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> briefApplyQueryHandlerException(@PathVariable("page") Integer page,
                                                                                  @PathVariable("limit") Integer limit,
                                                                                  @PathVariable("department") Integer department,
                                                                                  @PathVariable("stu_status_code") Integer stuStatusCode,
                                                                                  BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——根据姓名模糊查询学生集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> searchStuByNameHandlerException(@PathVariable("name") String name,
                                                                                  @PathVariable("page") Integer page,
                                                                                  @PathVariable("limit") Integer limit,
                                                                                  BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——根据学号模糊查询学生集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> searchStuByStuIdHandlerException(@PathVariable("stu_id") String stuId,
                                                                                   @PathVariable("page") Integer page,
                                                                                   @PathVariable("limit") Integer limit,
                                                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取调剂报名者简要信息集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> briefAdjustApplyQueryHandlerException(@PathVariable("page") Integer page,
                                                                                        @PathVariable("limit") Integer limit,
                                                                                        BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——根据姓名模糊查询调剂学生集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> searchAdjustStuByNameHandlerException(@PathVariable("name") String name,
                                                                                        @PathVariable("page") Integer page,
                                                                                        @PathVariable("limit") Integer limit,
                                                                                        BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——根据学号模糊查询调剂学生集合接口
     */
    public static ResultVO<PageDTO<BriefInfoDTO>> searchAdjustStuByStuIdHandlerException(@PathVariable("stu_id") String stuId,
                                                                                         @PathVariable("page") Integer page,
                                                                                         @PathVariable("limit") Integer limit,
                                                                                         BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——面试官评价接口
     */
    public static ResultVO<DetailedInfoDTO> addCommentHandlerException(CommentDTO commentDTO, BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——查询学生的所有评价
     */
    public static ResultVO<List<CommentDTO>> queryCommentsHandlerException(@PathVariable("stu_id") String stuId,
                                                                           BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取学生签到列表
     */
    public static ResultVO<List<CommentDTO>> getSignInListHandlerException(@PathVariable("deptId") Integer deptId,
                                                                           BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* ProcessStatusController */

    /**
     * Sentinel 异常处理——获取当前面试总进度
     */
    public static ResultVO<Integer> getNowProcessHandlerException(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel异常处理——获取当前各部门学生数量
     */
    public static ResultVO<List<Integer>> getDeptPeopleCountHandlerException(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel异常处理——获取学生的状态统计
     */
    public static ResultVO<List<Long>> getStatusPeopleCountHandlerException(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* StudentStatusController */

    /**
     * Sentinel 异常处理——面试开始面试接口
     */
    public static ResultVO<Integer> interviewStartHandlerException(@PathVariable("stu_id") String stuId,
                                                                   @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                   HttpServletRequest request,
                                                                   BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——二面开始面试接口
     *
     * @deprecated 一二面开始接口已合并，请使用 {@link #interviewStartHandlerException(String, String, HttpServletRequest, BlockException)}
     */
    @Deprecated
    public static ResultVO<Integer> secondInterviewStartHandlerException(@PathVariable("stu_id") String stuId,
                                                                         @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                         HttpServletRequest request,
                                                                         BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——面试通过接口
     */
    public static ResultVO<Integer> interviewPassHandlerException(@PathVariable("stu_id") String stuId,
                                                                  @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                  HttpServletRequest request,
                                                                  BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——二面通过接口
     *
     * @deprecated 一二面通过接口已合并，请使用 {@link #interviewPassHandlerException(String, String, HttpServletRequest, BlockException)}
     */
    @Deprecated
    public static ResultVO<Integer> secondInterviewPassHandlerException(@PathVariable("stu_id") String stuId,
                                                                        @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                        HttpServletRequest request,
                                                                        BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——二面调剂接口
     */
    public static ResultVO<Integer> secondInterviewAdjustHandlerException(@PathVariable("stu_id") String stuId,
                                                                          @PathVariable("department") Integer department,
                                                                          @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                          HttpServletRequest request,
                                                                          BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——二面调剂开始面试接口
     */
    public static ResultVO<Integer> secondAdjustInterviewStartHandlerException(@PathVariable("stu_id") String stuId,
                                                                               @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                               HttpServletRequest request,
                                                                               BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——二面调剂面试通过接口
     */
    public static ResultVO<Integer> secondAdjustInterviewPassHandlerException(@PathVariable("stu_id") String stuId,
                                                                              @PathVariable(value = "interviewer_username", required = false) String interviewerUsername,
                                                                              HttpServletRequest request,
                                                                              BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /* SuperAdminController */

    /**
     * Sentinel 异常处理——面试进度推进接口
     */
    public static ResultVO<Void> processAdvanceBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——发送一面通知接口
     */
    public static ResultVO<Void> firstInterviewNotifyBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——发送一面结果通知接口
     */
    public static ResultVO<Void> firstInterviewResultNotifyBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——发送二面通知接口
     */
    public static ResultVO<Void> secondInterviewNotifyBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——发送二面结果通知接口
     */
    public static ResultVO<Void> secondInterviewResultNotifyBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——发送笔试通知接口
     */
    public static ResultVO<Void> writtenTestNotifyBlockHandler(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取一面通过的学生列表
     */
    public static ResultVO<List<BriefPasserInfo>> getFirstInterviewPassListBlockHandler(Integer dept,
                                                                                        BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取电协最终录取的所有面试者名单
     */
    public static ResultVO<List<AdmissionStuDTO>> getFinalAdmissionListBlockHandler(Integer dept,
                                                                                    BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }

    /**
     * Sentinel 异常处理——获取报名列表
     */
    public static byte[] getApplyListBlockHandler(Integer deptId,
                                                  BlockException exception) {
        return null;
    }

    /**
     * Sentinel 异常处理——获取一面通过名单
     */
    public static byte[] getFirstListBlockHandler(Integer deptId,
                                                  BlockException exception) {
        return null;
    }

    /**
     * Sentinel 异常处理——获取二面通过名单
     */
    public static byte[] getSecondListBlockHandler(Integer deptId,
                                                   BlockException exception) {
        return null;
    }

    /**
     * Sentinel 异常处理——获取二面调剂通过名单
     */
    public static byte[] getSecondAdjustListBlockHandler(Integer deptId,
                                                         BlockException exception) {
        return null;
    }

}
