package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
 * 招新生产者接口
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
            return new ResultVO(ResultStatusCode.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        String openid = applyInfoDTO.getOpenid();
        //判断openid
        if(Boolean.FALSE.equals(stringRedisTemplate.opsForSet().isMember("user:stuId-openid", openid))){
            return new ResultVO(ResultStatusCode.FORBIDDEN,"openid缺失",null);
        }

        applyMapper.insert(applyInfoDTO);
        stringRedisTemplate.opsForSet().remove("user:stuId-openid",openid);
        return new ResultVO(ResultStatusCode.SUCCESS,"参数有误",null);
    }

    @Override
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid) {
        if(!GenericUtils.ofNullable(openid)){
            return new ResultVO<>(ResultStatusCode.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if(applyInfoDTO != null){
            return new ResultVO<>(ResultStatusCode.SUCCESS,"获取信息成功",applyInfoDTO);
        }
        return new ResultVO<>(ResultStatusCode.NOT_FIND,"搜索无果",null);
    }

    @Override
    public ResultVO<Integer> getStatus(String openid) {
        if(!GenericUtils.ofNullable(openid)){
            return new ResultVO<>(ResultStatusCode.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("status").eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if(applyInfoDTO != null && applyInfoDTO.getStatus() != null){
            return new ResultVO<>(ResultStatusCode.SUCCESS,"获取信息成功",applyInfoDTO.getStatus());
        }
        return new ResultVO<>(ResultStatusCode.NOT_FIND,"搜索无果",null);
    }

    @Override
    public ResultVO updateApplyInfo(String openid, ApplyInfoDTO applyInfoDTO) {
        if(!GenericUtils.allOfNullable(openid,applyInfoDTO) || applyInfoDTO.getOpenid() != null){
            return new ResultVO<>(ResultStatusCode.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        applyInfoDTO.setOpenid(openid);
        Wrapper<ApplyInfoDTO> wrapper = new UpdateWrapper<>();
        applyMapper.update(applyInfoDTO,wrapper);
        return new ResultVO(ResultStatusCode.SUCCESS,"更新成功",null);
    }
}
