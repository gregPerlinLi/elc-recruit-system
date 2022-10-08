package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.constant.RecruitStatusConstant;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IGetNameList;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author TUFSolareyes
 * @date 22/10/03
 */
@Service
public class GetNameListServiceImpl implements IGetNameList {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO<byte[]> getApplyList(Integer deptId) {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process <= RecruitStatusConstant.APPLY) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        File file = new File(ExportServiceImpl.TMP_PATH + ExportServiceImpl.APPLY_LIST + deptId + ".csv");
        if(!file.exists()) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无导出数据");
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"导出成功",bytes);
        } catch (IOException e) {}
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"导出错误");
    }

    @Override
    public ResultVO<byte[]> getFirstPassList(Integer deptId) {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process <= RecruitStatusConstant.FIRST_INTERVIEW) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        File file = new File(ExportServiceImpl.TMP_PATH + ExportServiceImpl.FIRST_LIST + deptId + ".csv");
        if(!file.exists()) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无导出数据");
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"导出成功",bytes);
        } catch (IOException e) {}
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"导出错误");
    }

    @Override
    public ResultVO<byte[]> getSecondPassList(Integer deptId) {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process <= RecruitStatusConstant.SECOND_INTERVIEW) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        File file = new File(ExportServiceImpl.TMP_PATH + ExportServiceImpl.SECOND_LIST + deptId + ".csv");
        if(!file.exists()) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无导出数据");
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"导出成功",bytes);
        } catch (IOException e) {}
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"导出错误");
    }

    @Override
    public ResultVO<byte[]> getSecondAdjustList(Integer deptId) {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process <= RecruitStatusConstant.SECOND_INTERVIEW) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        File file = new File(ExportServiceImpl.TMP_PATH + ExportServiceImpl.SECOND_LIST_ADJUST + deptId + ".csv");
        if(!file.exists()) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无导出数据");
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"导出成功",bytes);
        } catch (IOException e) {}
        return new ResultVO<>(ResultStatusCodeConstant.FAILED,"导出错误");
    }
}
