package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.ApplyMapper;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import com.gdutelc.recruit.utils.GenericUtils;
import com.gdutelc.recruit.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author TUFSolareyes
 * @date 22/08/09
 */
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public ResultVO apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException {
        //判空
        if(!GenericUtils.allOfNullable(applyInfoDTO)){
            return new ResultVO(ResultStatusCode.PARAM_EXCEPTION,"参数有误",null);
        }
        String openid = applyInfoDTO.getOpenid();
        //判断openid
        if(!stringRedisTemplate.opsForSet().isMember("user:stuId-opendi",openid)){
            return new ResultVO(ResultStatusCode.FORBIDDEN,"openid缺失",null);
        }

        applyMapper.insert(applyInfoDTO);
        stringRedisTemplate.opsForSet().remove("user:stuId-openid",openid);
        return new ResultVO(ResultStatusCode.SUCCESS,"参数有误",null);
    }
}
