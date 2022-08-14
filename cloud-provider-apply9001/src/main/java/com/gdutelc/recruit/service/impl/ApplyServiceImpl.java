package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.ApplyMapper;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import com.gdutelc.recruit.utils.GenericUtils;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 招新生产者接口
 * @author TUFSolareyes
 * @date 22/08/09
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public ResultVO<String> apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException {
        //判空
        if(!GenericUtils.allOfNullable(applyInfoDTO)){
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        String openid = applyInfoDTO.getOpenid();
        //判断openid
        if(Boolean.FALSE.equals(stringRedisTemplate.opsForSet().isMember("stu-openid", openid))){
            return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"openid缺失",null);
        }

        applyMapper.insert(applyInfoDTO);
        stringRedisTemplate.opsForSet().remove("stu-openid",openid);
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"报名成功",applyInfoDTO.getName());
    }

    @Override
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid) {
        if(!GenericUtils.ofNullable(openid)){
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if(applyInfoDTO != null){
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取信息成功",applyInfoDTO);
        }
        return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"搜索无果",null);
    }

    @Override
    public ResultVO<Integer> getStatus(String openid) {
        if(!GenericUtils.ofNullable(openid)){
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("status").eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if(applyInfoDTO != null && applyInfoDTO.getStatus() != null){
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取信息成功",applyInfoDTO.getStatus());
        }
        return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"搜索无果",null);
    }

    @Override
    public ResultVO<String> updateApplyInfo(ApplyInfoDTO applyInfoDTO) {
        if(!GenericUtils.ofNullable(applyInfoDTO) || !GenericUtils.ofNullable(applyInfoDTO.getOpenid()) || applyInfoDTO.getStatus() != 0){
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        UpdateWrapper<ApplyInfoDTO> wrapper = new UpdateWrapper<>();
        wrapper.eq("openid",applyInfoDTO.getOpenid());
        int update = applyMapper.update(applyInfoDTO, wrapper);
        if(update == 1){
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"更新成功",applyInfoDTO.getName());
        }else{
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"更新失败，请检查您的信息",null);
        }
    }

    @Override
    public ResultVO<Integer> signIn(String openid) throws NumberFormatException {
        if(!GenericUtils.ofNullable(openid)){
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        UpdateWrapper<ApplyInfoDTO> wrapper = new UpdateWrapper<>();
        wrapper.eq("openid",openid);
        wrapper.eq("status",0);
        ApplyInfoDTO applyInfoDTO = new ApplyInfoDTO();
        applyInfoDTO.setStatus(1);
        int update = applyMapper.update(applyInfoDTO, wrapper);
        if(update == 1){
            String process = stringRedisTemplate.opsForValue().get("process");
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"签到成功",Integer.parseInt(process));
        }else{
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"签到失败，请检查您的状态",null);
        }
    }
}
