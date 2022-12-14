package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * 报名模块生产者服务调用接口
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
    @PostMapping(value = "/pro/recruit/elc_access/apply")
    ResultVO<String> apply(ApplyInfoDTO applyInfoDTO) throws IllegalArgumentException;

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
     * @return {@link ResultVO}，其中数据为面试状态号
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_allStatus/{openid}")
    @ResponseBody
    ResultVO<Integer> getAllStatus(@PathVariable("openid") String openid);

    /**
     * 生产者获取当前以前到学生签到状态接口
     *
     * @param openid 微信openid
     * @return {@link ResultVO}，其中数据为面试状态号
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_signInStatus/{openid}")
    @ResponseBody
    ResultVO<Integer> getSignInStatus(@PathVariable("openid") String openid);

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
     * @param key 签到用的二维码
     * @return {@link ResultVO}，其中数据为当前面试总进度代码
     */
    @PutMapping(value = "/pro/recruit/elc_access/sign_in/{openid}/{key}")
    ResultVO<Integer> signIn(@PathVariable("openid") String openid ,@PathVariable("key") String key);
}
