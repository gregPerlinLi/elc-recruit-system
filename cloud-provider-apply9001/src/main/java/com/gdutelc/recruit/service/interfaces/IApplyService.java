package com.gdutelc.recruit.service.interfaces;


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
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     * @throws IllegalAccessException 参数异常
     */
    ResultVO apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException;
}
