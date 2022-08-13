package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 报名模块生产者接口
 * @author TUFSolareyes
 * @date 2022-08-07
 */
@RestController
@RequestMapping(value = "/pro/recruit/elc_access")
public class ApplyAccessController {

    @Resource
    private IApplyService iApplyService;

    /**
     * 报名接口，提交个人详细信息
     * @param applyInfoDTO 报名信息实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping(value = "/apply")
    @ResponseBody
    public ResultVO<String> apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException {
        return iApplyService.apply(applyInfoDTO);
    }

    /**
     * 获取个人完整报名信息接口
     * @param openid 微信openid
     * @return ResultVO，其中数据为个人报名信息实体类
     */
    @GetMapping(value = "/get_apply_info")
    @ResponseBody
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid){
        return iApplyService.getApplyInfo(openid);
    }

    /**
     * 获取当前学生的面试状态接口
     *
     * @param openid 微信 openid
     * @return 面试状态号
     */
    @GetMapping(value = "/get_status")
    @ResponseBody
    public ResultVO<Integer> getStatus(String openid){
        return iApplyService.getStatus(openid);
    }

    /**
     * 修改学生面试信息接口
     * @param applyInfoDTO 要更新的学生报名信息
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PutMapping(value = "/update_apply_info")
    @ResponseBody
    public ResultVO<String> updateApplyInfo(ApplyInfoDTO applyInfoDTO){
        return iApplyService.updateApplyInfo(applyInfoDTO);
    }


    /**
     * 签到接口
     * @param openid 微信openid
     * @return 名字
     */
    public ResultVO<String> signIn(String openid){
        return iApplyService.signIn(openid);
    }


}
