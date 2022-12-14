package com.gdutelc.recruit.service.interfaces;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * @author TUFSolareyes
 * @date 22/08/09
 */
public interface IApplyService {

    /**
     * 报名服务
     *
     * @param applyInfoDTO 报名信息
     * @return {@link ResultVO}，其中包含报名者姓名
     * @throws IllegalAccessException 参数异常
     */
    ResultVO<String> apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException;

    /**
     * 获取自己的报名信息
     * @param openid 微信的openid
     * @return {@link ResultVO}
     */
    ResultVO<ApplyInfoDTO> getApplyInfo(String openid);

    /**
     * 获取自己当前状态
     * @param openid 微信的openid
     * @return {@link ResultVO}
     */
    ResultVO<Integer> getAllStatus(String openid);

    /**
     * 获取签到状态
     * @param openid 微信的openid
     * @return {@link ResultVO}
     */
    ResultVO<Integer> getSignInStatus(String openid);

    /**
     * 更新报名信息，openid不能修改，修改信息中这个字段是为了定位
     * @param applyInfoDTO 修改信息的实体类，要修改的字段赋值，不修改的直接不传参就行
     * @return {@link ResultVO}
     */
    ResultVO<String> updateApplyInfo(ApplyInfoDTO applyInfoDTO);

    /**
     * 签到，将状态值更新为1
     * @param openid
     * @param key
     * @return {@link ResultVO}
     * @throws NumberFormatException 数值转换异常
     */
    ResultVO<Integer> signIn(String openid,String key) throws NumberFormatException, JsonProcessingException;
}
