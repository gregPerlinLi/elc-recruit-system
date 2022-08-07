package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.entities.dto.ApplyInfoDTO;
import com.gdutelc.recruit.entities.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 报名模块消费者服务层接口
 * @author TUFSolareyes
 */
@Service
@FeignClient
public interface ApplyService {

    @PostMapping("/recruit/elc_access/apply")
    ResultVO apply(ApplyInfoDTO applyInfoDTO);
}
