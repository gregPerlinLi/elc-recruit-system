package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 报名模块消费者服务层接口
 * @author TUFSolareyes
 */
@Service
@FeignClient("cloud-provider-apply")
public interface IApplyService {

    /**
     * 生产者报名接口
     *
     * @param applyInfoDTO 报名信息
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @PostMapping("/pro/recruit/elc_access/apply")
    ResultVO apply(ApplyInfoDTO applyInfoDTO);
}
