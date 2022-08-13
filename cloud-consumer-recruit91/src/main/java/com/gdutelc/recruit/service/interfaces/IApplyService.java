package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * 报名模块消费者服务层接口
 *
 * @author gregPerlinLi
 * @date 2022-8-9
 */
@Service
@FeignClient(name = "cloud-provider-apply", contextId = "ApplyServiceApi")
public interface IApplyService {

    /**
     * 生产者报名接口
     *
     * @param applyInfoDTO 报名信息
     * @return {@link ResultVO}，其中数据为报名者姓名
     */
    @PostMapping("/pro/recruit/elc_access/apply")
    ResultVO<String> apply(ApplyInfoDTO applyInfoDTO);

    /**
     * 生产者获取个人完整报名信息接口
     *
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为个人报名信息实体类
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_apply_info/{openid}")
    ResultVO<ApplyInfoDTO> getApplyInfo(@PathVariable("openid") String openid);

    /**
     * 生产者获取当前学生的面试状态接口
     *
     * @param openid 微信 openid
     * @return {@link ResultVO}其中数据为面试状态号
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_status/{openid}")
    @ResponseBody
    ResultVO<Integer> getStatus(@PathVariable("openid") String openid);

    /**
     * 生产者修改学生面试信息接口
     *
     * @param applyInfoDTO 要更新的学生报名信息
     * @return {@link ResultVO}，其中数据为报名者姓名
     */
    @PutMapping(value = "/pro/recruit/elc_access/update_apply_info")
    ResultVO<String> updateApplyInfo(ApplyInfoDTO applyInfoDTO);

    /**
     * 生产者签到接口
     *
     * @param openid 签到的学生微信id
     * @return {@link ResultVO}，其中数据为当前面试总进度代码
     */
    @PutMapping(value = "/pro/recruit/elc_access/sign_in/{openid}")
    ResultVO<Integer> signIn(@PathVariable("openid") String openid);
}
