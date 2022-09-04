package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdutelc.recruit.constant.RecruitStatusConstant;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.OverAllProgress;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TUFSolareyes
 * @date 22/09/03
 */
public class OverAllProgressImpl implements OverAllProgress {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private StuInfoMapper stuInfoMapper;

    @Resource
    private WeChatServerService weChatServerService;

    @Override
    public ResultVO overAllProgress() throws NumberFormatException{
        //获取当前总进度
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        UpdateWrapper<StuInfo> updateWrapper = new UpdateWrapper<>();
        Map<String,Object> map = new HashMap<>();
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
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + 10 + "");

            map.put("status",StudentStatusConstant.REGISTERED);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            for(int i=0;i<stuInfos.size();i++) {
                weChatServerService.sendSecondInterviewNotify(stuInfos.get(i).getOpenid());
            }
        }else if(currentProgress == RecruitStatusConstant.SECOND_INTERVIEW) {
            //如果当前是二面，就将通过的状态置为10
            StuInfo stuInfo = new StuInfo();
            stuInfo.setStatus(StudentStatusConstant.EMPLOYMENT);
            updateWrapper.eq("status",StudentStatusConstant.PASS);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stuInfo.setStatus(StudentStatusConstant.FAILED);
            updateWrapper.eq("status",StudentStatusConstant.INTERVIEWING);
            stuInfoMapper.update(stuInfo,updateWrapper);
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + 10 + "");

            map.put("status",StudentStatusConstant.EMPLOYMENT);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            for(int i=0;i<stuInfos.size();i++) {
                weChatServerService.sendFinallyPassedNotify(stuInfos.get(i).getOpenid());
            }
        }else if(currentProgress == RecruitStatusConstant.APPLY) {
            //如果当前是报名，就推进到一面
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.PROCESS,currentProgress + 10 + "");
            map.put("status",StudentStatusConstant.REGISTERED);
            List<StuInfo> stuInfos = stuInfoMapper.selectByMap(map);
            for(int i=0;i<stuInfos.size();i++) {
                weChatServerService.sendFirstInterviewNotify(stuInfos.get(i).getOpenid());
            }
        }
        return null;
    }
}
