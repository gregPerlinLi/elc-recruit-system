package com.gdutelc.recruit.service.interfaces;


import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * @author TUFSolareyes
 * @date 22/08/09
 */
public interface ApplyService {

    ResultVO apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException;
}
