package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 报名模块生产者接口
 * @author TUFSolareyes
 * @date 2022-08-07
 */
@Controller
@RequestMapping(value = "/pro/recruit/elc_access")
public class ApplyAccessController {

    /**
     * 报名接口，提交个人详细信息
     * @param applyInfoDTO 报名信息实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping(value = "/apply")
    @ResponseBody
    public ResultVO apply(ApplyInfoDTO applyInfoDTO){
        return null;
    }

    /**
     * 获取个人完整报名信息接口
     * @param openid 微信openid
     * @return ResultVO，其中数据为个人报名信息实体类
     */
    @GetMapping(value = "/get_apply_info")
    @ResponseBody
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid){
        return null;
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
        return null;
    }

    /**
     * 修改学生面试信息接口
     *
     * @param openid 微信 openid
     * @param applyInfoDTO 要更新的学生报名信息
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PutMapping(value = "/update_apply_info")
    @ResponseBody
    public ResultVO updateApplyInfo(String openid,ApplyInfoDTO applyInfoDTO){
        return null;
    }

}