package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @date 22/08/07
 */
@Slf4j
@RestController
@RequestMapping(value = "/recruit/elc_access")
public class ApplyController {

    @Resource
    private IApplyService applyService;

    /**
     * 报名接口
     *
     * @param applyInfoDTO 报名信息
     * @return {@link ResultVO} 其中数据为报名者姓名
     */
    @PostMapping(value = "/apply")
    @SentinelResource(value = "apply", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "applyHandlerException")
    @ApiOperation(value = "报名", tags = "apply", response = ResultVO.class)
    public ResultVO<String> apply (@ApiParam(value = "报名信息", required = true) ApplyInfoDTO applyInfoDTO) {
        log.warn(applyInfoDTO.toString());
        ResultVO<String> result = applyService.apply(applyInfoDTO);
        if ( result.getCode()  == ResultStatusCodeConstant.SUCCESS ) {
            // TODO: 微信推送报名成功信息
        }
        return result;
    }

    /**
     * 获取个人完整报名信息接口
     *
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为个人报名实体类信息
     */
    @GetMapping(value = "/get_apply_info/{openid}")
    @SentinelResource(value = "getApplyInfo", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getApplyInfoHandlerException")
    @ApiOperation(value = "获取个人完整报名信息", tags = "apply", response = ResultVO.class)
    public ResultVO<ApplyInfoDTO> getApplyInfo(@ApiParam(value = "微信openid", required = true) @PathVariable("openid") String openid) {
        return applyService.getApplyInfo(openid);
    }

    /**
     * 获取当前学生的面试状态接口
     *
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为面试状态号
     */
    @GetMapping(value = "/get_status/{openid}")
    @SentinelResource(value = "getStatus", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getStatusHandlerException")
    @ApiOperation(value = "获取当前学生面试状态接口", tags = "apply", response = ResultVO.class)
    public ResultVO<Integer> getStatus(@ApiParam(value = "微信openid", required = true) @PathVariable("openid") String openid) {
        return  applyService.getStatus(openid);
    }

    /**
     * 修改学生面试信息接口
     *
     * @param applyInfoDTO 要更新的学生报名信息
     * @return {@link ResultVO}，其中数据为报名者姓名
     */
    @PutMapping(value = "/update_apply_info")
    @SentinelResource(value = "updateApplyInfo", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "updateApplyInfoHandlerException")
    @ApiOperation(value = "修改学生面试信息接口", tags = "apply", response = ResultVO.class)
    public ResultVO<String> updateApplyInfo(@ApiParam(value = "要更新的学生报名信息", required = true) ApplyInfoDTO applyInfoDTO) {
        return applyService.updateApplyInfo(applyInfoDTO);
    }

    /**
     * 学生签到接口
     *
     * @param openid 要签到的学生微信openid
     * @return {@link ResultVO}，其中数据为当前面试总进度代码
     */
    @PutMapping(value = "sign_in/{openid}")
    @SentinelResource(value = "singIn", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "signInHandlerException")
    @ApiOperation(value = "学生签到接口", tags = "apply", response = ResultVO.class)
    public ResultVO<Integer> signIn(@ApiParam(value = "要签到的学生微信openid", required = true) @PathVariable("openid") String openid) {
        ResultVO<Integer> result = applyService.signIn(openid);
        if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
            // TODO: 这里是微信发送签到成功消息的调用
        }
        return result;
    }

}
