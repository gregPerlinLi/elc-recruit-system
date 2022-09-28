package com.gdutelc.recruit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 报名模块生产者接口
 * @author TUFSolareyes
 * @date 2022-08-07
 */
@Slf4j
@RestController
@RequestMapping(value = "/pro/recruit/elc_access")
public class ApplyAccessController {

    @Resource
    private IApplyService applyService;

    /**
     * 报名接口，提交个人详细信息
     * @param applyInfoDTO 报名信息实体类
     * @return {@link ResultVO}，其中数据为报名者姓名
     */
    @PostMapping(value = "/apply")
    public ResultVO<String> apply(@RequestBody ApplyInfoDTO applyInfoDTO) throws IllegalAccessException {
        return applyService.apply(applyInfoDTO);
    }

    /**
     * 获取个人完整报名信息接口
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为个人报名信息实体类
     */
    @GetMapping(value = "/get_apply_info/{openid}")
    public ResultVO<ApplyInfoDTO> getApplyInfo(@PathVariable("openid") String openid){
        return applyService.getApplyInfo(openid);
    }

    /**
     * 获取当前学生的面试状态接口
     *
     * @param openid 微信 openid
     * @return {@link ResultVO}，其中数据为面试状态号
     */
    @GetMapping(value = "/get_allStatus/{openid}")
    public ResultVO<Integer> getAllStatus(@PathVariable("openid") String openid){
        return applyService.getAllStatus(openid);
    }

    /**
     * 获取当前学生的签到状态接口
     *
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为当前签到状态
     */
    @GetMapping(value = "/get_signInStatus/{openid}")
    public ResultVO<Integer> getSignInStatus(@PathVariable("openid") String openid) {
        System.out.println(openid);
        return applyService.getSignInStatus(openid);
    }


    /**
     * 修改学生面试信息接口
     *
     * @param applyInfoDTO 要更新的学生报名信息
     * @return {@link ResultVO}，其中数据为报名者姓名
     */
    @PutMapping(value = "/update_apply_info")
    public ResultVO<String> updateApplyInfo(@RequestBody ApplyInfoDTO applyInfoDTO){
        log.info(applyInfoDTO.toString());
        return applyService.updateApplyInfo(applyInfoDTO);
    }

    /**
     * 学生签到接口
     *
     * @param openid 学生微信openid
     * @param key 签到二维码
     * @return {@link ResultVO}，其中数据为当前面试总进度代码
     */
    @PutMapping(value = "/sign_in/{openid}/{key}")
    public ResultVO<Integer> signIn(@PathVariable("openid") String openid,@PathVariable("key") String key) throws JsonProcessingException {
        return applyService.signIn(openid,key);
    }

}
