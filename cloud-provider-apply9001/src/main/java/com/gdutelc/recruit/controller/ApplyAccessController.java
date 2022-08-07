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
@RequestMapping("/recruit/elc_access")
public class ApplyAccessController {


    /**
     * 报名接口，提交个人详细信息
     * @param applyInfoDTO 报名信息实体类
     * @return ResultVO，其中不包含数据，只包含状态码和信息
     */
    @PostMapping("/apply")
    @ResponseBody
    public ResultVO apply(ApplyInfoDTO applyInfoDTO){
        return null;
    }

    /**
     * 获取个人完整报名信息接口
     * @param openid
     * @return ResultVO，其中数据为个人报名信息实体类
     */
    @GetMapping("/get_apply_info")
    @ResponseBody
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid){
        return null;
    }

    /**
     *
     * @param openid
     * @return
     */
    @GetMapping("/get_status")
    @ResponseBody
    public ResultVO<Integer> getStatus(String openid){
        return null;
    }


    @PutMapping("/update_apply_info")
    @ResponseBody
    public ResultVO updateApplyInfo(String openid,ApplyInfoDTO applyInfoDTO){
        return null;
    }

}
