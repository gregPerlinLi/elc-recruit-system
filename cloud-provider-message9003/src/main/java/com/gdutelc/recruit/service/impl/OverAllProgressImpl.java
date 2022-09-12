package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdutelc.recruit.constant.RecruitStatusConstant;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IOverAllProgress;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TUFSolareyes
 * @date 22/09/03
 */
@Service
public class OverAllProgressImpl implements IOverAllProgress {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private StuInfoMapper stuInfoMapper;

    @Resource
    private WeChatServerService weChatServerService;

    @Override
    public ResultVO<Integer> overAllProgress() throws NumberFormatException{
        //获取当前总进度
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        UpdateWrapper<StuInfo> updateWrapper = new UpdateWrapper<>();
        Map<String,Object> map = new HashMap<>(1);
        if(currentProgress == RecruitStatusConstant.FIRST_INTERVIEW) {
            //如果当前是一面，就将通过的状态重置为1
            StuInfo stuInfo = new StuInfo();
            stuInfo.setStatus(StudentStatusConstant.REGISTERED);
            updateWrapper.eq("status", StudentStatusConstant.PASS);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stuInfo.setStatus(StudentStatusConstant.FAILED);
            updateWrapper.clear();
            updateWrapper.eq("status",StudentStatusConstant.INTERVIEWING);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + RecruitStatusConstant.STEP + "");

            map.put("status",StudentStatusConstant.REGISTERED);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            try {
                for ( StuInfo info : stuInfos ) {
                    weChatServerService.sendSecondInterviewNotify(info.getOpenid());
                }
            }catch (Exception e){}
        }else if(currentProgress == RecruitStatusConstant.SECOND_INTERVIEW) {
            //如果当前是二面，就将通过的状态置为10
            StuInfo stuInfo = new StuInfo();
            stuInfo.setStatus(StudentStatusConstant.EMPLOYMENT);
            updateWrapper.eq("status",StudentStatusConstant.PASS);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stuInfo.setStatus(StudentStatusConstant.FAILED);
            updateWrapper.eq("status",StudentStatusConstant.INTERVIEWING);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + RecruitStatusConstant.STEP + "");

            map.put("status",StudentStatusConstant.EMPLOYMENT);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            try{
                for ( StuInfo info : stuInfos ) {
                    weChatServerService.sendFinallyPassedNotify(info.getOpenid());
                }
            }catch (Exception e){}
        }else if(currentProgress == RecruitStatusConstant.APPLY) {
            //如果当前是报名，就推进到一面
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + RecruitStatusConstant.STEP + "");
            map.put("status",StudentStatusConstant.REGISTERED);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            try {
                for ( StuInfo stuInfo : stuInfos ) {
                    weChatServerService.sendFirstInterviewNotify(stuInfo.getOpenid());
                }
            }catch (Exception e){}
        }
        int current = Integer.parseInt(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS));
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"进度已成功推进",current);
    }
}
