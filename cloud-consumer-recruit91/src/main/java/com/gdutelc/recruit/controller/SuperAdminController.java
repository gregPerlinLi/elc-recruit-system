package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.dto.AdmissionStuDTO;
import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IMessageService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 超级管理员相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@RestController
@RequestMapping(value = "/super_admin/elc_access")
public class SuperAdminController {

    @Resource
    IMessageService messageService;

    /**
     * 面试进度推进接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PutMapping(value = "/process_advance")
    @SentinelResource(value = "processAdvance", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "processAdvanceBlockHandler")
    @ApiOperation(value = "面试进度推进", tags = "super_admin", response = ResultVO.class)
    public ResultVO<Integer> processAdvance() {
        return messageService.overAllProgress();
    }

    /**
     * 发送一面通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/first_interview_notify")
    @SentinelResource(value = "firstInterviewNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "firstInterviewNotifyBlockHandler")
    @ApiOperation(value = "发送一面通知", tags = "notify", response = ResultVO.class)
    public ResultVO<Void> firstInterviewNotify() {
        return messageService.firstInterviewNotify();
    }

    /**
     * 发送一面结果通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/first_interview_result_notify")
    @SentinelResource(value = "firstInterviewResultNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "firstInterviewResultNotifyBlockHandler")
    @ApiOperation(value = "发送一面结果通知", tags = "notify", response = ResultVO.class)
    public ResultVO<Void> firstInterviewResultNotify() {
        return messageService.firstInterviewResultNotify();
    }

    /**
     * 发送二面通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/second_interview_notify")
    @SentinelResource(value = "secondInterviewNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewNotifyBlockHandler")
    @ApiOperation(value = "发送二面通知", tags = "notify", response = ResultVO.class)
    public ResultVO<Void> secondInterviewNotify() {
        return messageService.secondInterviewNotify();
    }

    /**
     * 发送二面结果通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/second_interview_result_notify")
    @SentinelResource(value = "secondInterviewResultNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewResultNotifyBlockHandler")
    @ApiOperation(value = "发送二面结果通知", tags = "notify", response = ResultVO.class)
    public ResultVO<Void> secondInterviewResultNotify() {
        return messageService.secondInterviewResultNotify();
    }

    /**
     * 发送笔试通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/written_test_notify")
    @SentinelResource(value = "writtenTestNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "writtenTestNotifyBlockHandler")
    @ApiOperation(value = "发送笔试通知", tags = "notify", response = ResultVO.class)
    public ResultVO<Void> writtenTestNotify() {
        return messageService.writtenTestNotify();
    }

    /**
     * 获取一面通过的学生列表
     *
     * @param dept 部门筛选（{@code 0}代表不筛选部门）
     * @return {@link ResultVO}，其中包含数据{@link List<StuInfo>}，和状态码和信息
     */
    @GetMapping(value = "/get_first_interview_pass_list/{dept}")
    @SentinelResource(value = "getFirstInterviewPassList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getFirstInterviewPassListBlockHandler")
    @ApiOperation(value = "获取一面通过的学生列表", tags = "super_admin", response = ResultVO.class)
    public ResultVO<List<BriefPasserInfo>> getFirstInterviewPassList(@ApiParam(value = "筛选部门", required = true) @PathVariable("dept") Integer dept) {
        return messageService.getFirstInterviewPassList(dept);
    }

    /**
     * 获取电协最终录取的所有面试者名单
     *
     * @param dept 部门筛选（{@code 0}代表不筛选部门）
     * @return {@link ResultVO}，其中包含数据{@link List<AdmissionStuDTO>}，和状态码和信息
     */
    @GetMapping(value = "/get_final_admission_list/{dept}")
    @SentinelResource(value = "getFinalAdmissionList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getFinalAdmissionListBlockHandler")
    @ApiOperation(value = "获取电协最终录取的所有面试者名单", tags = "super_admin", response = ResultVO.class)
    public ResultVO<List<AdmissionStuDTO>> getFinalAdmissionList(@ApiParam(value = "部门筛选", required = true) @PathVariable("dept") Integer dept) {
        return messageService.getFinalAdmissionList(dept);
    }


    /**
     * 获取报名列表
     *
     * @param deptId 部门ID
     * @return 字节流
     */
    @GetMapping(value = "/getApply/{deptId}")
    @SentinelResource(value = "getApplyList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getApplyListBlockHandler")
    @ApiOperation(value = "获取报名列表", tags = "downloadForms", response = Byte.class)
    public byte[] getApplyList(@PathVariable("deptId") Integer deptId) {
        System.out.println(deptId);
        return messageService.getApplyList(deptId).getData();
    }


    /**
     * 获取一面通过名单
     *
     * @param deptId 部门ID
     * @return 字节流
     */
    @GetMapping(value = "/getFirst/{deptId}")
    @SentinelResource(value = "getFirstList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getFirstListBlockHandler")
    @ApiOperation(value = "获取一面通过名单", tags = "downloadForms", response = Byte.class)
    public byte[] getFirstList(@PathVariable("deptId") Integer deptId) {
        System.out.println(deptId);
        return messageService.getFirstList(deptId).getData();
    }


    /**
     * 获取二面通过名单
     *
     * @param deptId 部门ID
     * @return 字节流
     */
    @GetMapping(value = "/getSecond/{deptId}")
    @SentinelResource(value = "getSecondList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getSecondListBlockHandler")
    @ApiOperation(value = "获取二面通过名单", tags = "downloadForms", response = Byte.class)
    public byte[] getSecondList(@PathVariable("deptId") Integer deptId) {
        return messageService.getSecondList(deptId).getData();
    }


    /**
     * 获取二面调剂通过名单
     *
     * @param deptId 部门ID
     * @return 字节流
     */
    @GetMapping(value = "/getSecondAdjustList/{deptId}")
    @SentinelResource(value = "getSecondAdjustList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getSecondAdjustListBlockHandler")
    @ApiOperation(value = "获取二面调剂通过名单", tags = "downloadForms", response = Byte.class)
    public byte[] getSecondAdjustList(@PathVariable("deptId") Integer deptId) {
        return messageService.getSecondAdjustList(deptId).getData();
    }

}
