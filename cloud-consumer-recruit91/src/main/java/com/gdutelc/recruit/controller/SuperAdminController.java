package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 超级管理员相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@RestController
@RequestMapping(value = "/recruit/elc_access")
public class SuperAdminController {

    /**
     * 面试进度推进接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PutMapping(value = "/process_advance")
    @SentinelResource(value = "processAdvance", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "processAdvanceBlockHandler")
    @ApiOperation(value = "面试进度推进", tags = "super_admin", response = ResultVO.class)
    public ResultVO processAdvance() {
        return null;
    }

    /**
     * 发送一面通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/first_interview_notify")
    @SentinelResource(value = "firstInterviewNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "firstInterviewNotifyBlockHandler")
    @ApiOperation(value = "发送一面通知", tags = "super_admin", response = ResultVO.class)
    public ResultVO firstInterviewNotify() {
        return null;
    }

    /**
     * 发送二面通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/second_interview_notify")
    @SentinelResource(value = "secondInterviewNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "secondInterviewNotifyBlockHandler")
    @ApiOperation(value = "发送二面通知", tags = "super_admin", response = ResultVO.class)
    public ResultVO secondInterviewNotify() {
        return null;
    }

    /**
     * 发送笔试通知接口
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/written_test_notify")
    @SentinelResource(value = "writtenTestNotify", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "writtenTestNotifyBlockHandler")
    @ApiOperation(value = "发送笔试通知", tags = "super_admin", response = ResultVO.class)
    public ResultVO writtenTestNotify() {
        return null;
    }

    /**
     * 获取一面通过的学生列表
     *
     * @return {@link ResultVO}，其中包含数据{@link List<StuInfo>}，和状态码和信息
     */
    @GetMapping(value = "/get_first_interview_pass_list")
    @SentinelResource(value = "getFirstInterviewPassList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getFirstInterviewPassListBlockHandler")
    @ApiOperation(value = "获取一面通过的学生列表", tags = "super_admin", response = ResultVO.class)
    public ResultVO<List<StuInfo>> getFirstInterviewPassList() {
        return null;
    }

    /**
     * 获取电协最终录取的所有面试者名单
     *
     * @return {@link ResultVO}，其中包含数据{@link List<StuInfo>}，和状态码和信息
     */
    @GetMapping(value = "/get_final_admission_list")
    @SentinelResource(value = "getFinalAdmissionList", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getFinalAdmissionListBlockHandler")
    @ApiOperation(value = "获取电协最终录取的所有面试者名单", tags = "super_admin", response = ResultVO.class)
    public ResultVO<List<StuInfo>> getFinalAdmissionList() {
        return null;
    }
}
