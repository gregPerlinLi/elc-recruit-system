package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdutelc.recruit.constant.*;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import com.gdutelc.recruit.domain.dto.SignInDTO;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.ApplyMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IApplyService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 招新生产者接口
 * @author TUFSolareyes
 * @date 22/08/09
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Resource
    private ApplyMapper applyMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${checkin.first}")
    private String firstKey;

    @Value("${checkin.second}")
    private String secondKey;

    @Override
    public ResultVO<String> apply(ApplyInfoDTO applyInfoDTO) throws IllegalAccessException {
        //判空
        if ( !GenericUtils.allOfNullable(applyInfoDTO) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        String openid = applyInfoDTO.getOpenid();
        //判断openid
        if ( Boolean.FALSE.equals(stringRedisTemplate.opsForSet().isMember(RedisKeyConstant.STU_OPENID, openid)) ) {
            return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"openid缺失",null);
        }
        // 判断是否已经到了开始面试的进度
        if ( !Objects.equals(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS), Integer.toString(RecruitStatusConstant.APPLY))) {
            return new ResultVO<>(ResultStatusCodeConstant.STATUS_EXCEPTION,"不在报名阶段",null);
        }
        int in = applyMapper.insert(applyInfoDTO);
        if(in == 1) {
            stringRedisTemplate.opsForSet().remove(RedisKeyConstant.STU_OPENID, openid);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"报名成功",applyInfoDTO.getName());
        }
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"报名失败",applyInfoDTO.getName());
    }

    @Override
    public ResultVO<ApplyInfoDTO> getApplyInfo(String openid) {
        if ( !GenericUtils.ofNullable(openid) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if ( applyInfoDTO != null ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取信息成功",applyInfoDTO);
        }
        return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"搜索无果",null);
    }

    @Override
    public ResultVO<Integer> getAllStatus(String openid) throws NumberFormatException{
        if ( !GenericUtils.ofNullable(openid) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        String curStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer cur = Integer.parseInt(curStr);
        QueryWrapper<ApplyInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("status").eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(queryWrapper);
        if(!GenericUtils.ofNullable(applyInfoDTO) || !GenericUtils.ofNullable(applyInfoDTO.getStatus())) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"搜索无果",null);
        }
        if(applyInfoDTO.getStatus() == -1) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取信息成功",TaroStudentStatusConstant.FAILED);
        }

        int ans = -2;
        switch (cur) {
            case RecruitStatusConstant.APPLY -> {
                ans = TaroStudentStatusConstant.IN_APPLY;
            }
            case RecruitStatusConstant.FIRST_INTERVIEW -> {
                ans = TaroStudentStatusConstant.FIRST_INTERVIEW;
            }
            case RecruitStatusConstant.WRITTEN_EXAM -> {
                ans = TaroStudentStatusConstant.WRITTEN_EXAM;
            }
            case RecruitStatusConstant.SECOND_INTERVIEW -> {
                ans = TaroStudentStatusConstant.SECOND_INTERVIEW;
            }
            case RecruitStatusConstant.END -> {
                ans = TaroStudentStatusConstant.END;
            }
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取信息成功",ans);
    }

    @Override
    public ResultVO<Integer> getSignInStatus(String openid) {
        if ( !GenericUtils.ofNullable(openid) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        QueryWrapper<StuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        StuInfo stuInfo = stuInfoMapper.selectOne(wrapper);
        Integer status = stuInfo.getStatus();
        if(status == StudentStatusConstant.CHECKED_IN) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"签到成功",TaroStudentStatusConstant.SIGNIN_SUCCESS);
        }
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"签到失败",TaroStudentStatusConstant.SIGNIN_FAILED);
    }

    @Override
    public ResultVO<String> updateApplyInfo(ApplyInfoDTO applyInfoDTO) {
        if ( !GenericUtils.ofNullable(applyInfoDTO) || !GenericUtils.ofNullable(applyInfoDTO.getOpenid()) || applyInfoDTO.getStatus() != 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        // 判断是否已经到了开始面试的进度
        if ( !Objects.equals(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS), Integer.toString(RecruitStatusConstant.APPLY))) {
            return new ResultVO<>(ResultStatusCodeConstant.STATUS_EXCEPTION,"不在报名阶段",null);
        }
        UpdateWrapper<ApplyInfoDTO> wrapper = new UpdateWrapper<>();
        wrapper.eq("openid",applyInfoDTO.getOpenid());
        int update = applyMapper.update(applyInfoDTO, wrapper);
        if ( update == 1 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"更新成功",applyInfoDTO.getName());
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"更新失败，请检查您的信息",null);
        }
    }

    @Override
    public ResultVO<Integer>  signIn(String openid,String key) throws NumberFormatException, JsonProcessingException {
        if ( !GenericUtils.ofNullable(openid) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        String curProcessStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer curProcess = Integer.parseInt(curProcessStr);
        if(curProcess == RecruitStatusConstant.APPLY) {
            return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"签到失败，当前状态不符合",null);
        }else if(curProcess == RecruitStatusConstant.FIRST_INTERVIEW || curProcess == RecruitStatusConstant.WRITTEN_EXAM) {
            //如果不是一面/笔试阶段则不能签到
            if(!firstKey.equals(key)) {
//                System.out.println(firstKey + " " + key);
                return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"签到码有问题",null);
            }
        }else if(curProcess == RecruitStatusConstant.SECOND_INTERVIEW) {
            //如果不是二面阶段则不能签到
            if(!secondKey.equals(key)) {
                return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"签到码有问题",null);
            }
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN,"签到失败，当前状态不符合",null);
        }

        UpdateWrapper<ApplyInfoDTO> wrapper = new UpdateWrapper<>();
        wrapper.eq("openid",openid);
        wrapper.eq("status",StudentStatusConstant.REGISTERED);
        System.out.println(openid);
        ApplyInfoDTO applyInfoDTO = new ApplyInfoDTO();
        applyInfoDTO.setStatus(StudentStatusConstant.CHECKED_IN);
        int update = applyMapper.update(applyInfoDTO, wrapper);
        System.out.println(update);
        if ( update == 1 ) {
            String process = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
            signInQueue(openid);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"签到成功",Integer.parseInt(process));
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"签到失败，请检查您的状态",null);
        }
    }

    /**
     * 签到加入队列
     */
    private void signInQueue(String openid) throws JsonProcessingException {
        QueryWrapper<ApplyInfoDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        ApplyInfoDTO applyInfoDTO = applyMapper.selectOne(wrapper);
        String stuName = applyInfoDTO.getName();
        String stuId = applyInfoDTO.getStuId();
        if(!GenericUtils.ofNullable(stuName) || !GenericUtils.ofNullable(stuId)) {
            return;
        }
        Integer firstDept = applyInfoDTO.getFirstDept();
        SignInDTO signInDto = new SignInDTO(stuName,stuId);
        String jsonStr = objectMapper.writeValueAsString(signInDto);
        stringRedisTemplate.opsForList().rightPush(RedisKeyConstant.SIGN_IN + firstDept,jsonStr);
    }
}
