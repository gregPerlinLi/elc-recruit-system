package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;
import com.gdutelc.recruit.mapper.AdjustStuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IAdjustStuInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 调剂学生名单 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class AdjustStuInfoServiceImpl extends ServiceImpl<AdjustStuInfoMapper, AdjustStuInfo> implements IAdjustStuInfoService {

    @Resource
    AdjustStuInfoMapper adjustStuInfoMapper;

    @Override
    public Integer interviewStart(String stuId) {
        QueryWrapper<AdjustStuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        AdjustStuInfo adjustStuInfo = getOne(queryWrapper);
        if ( adjustStuInfo == null ) {
            return 0;
        } else {
            UpdateWrapper<AdjustStuInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id", stuId);
            updateWrapper.eq("status", StudentStatusConstant.CHECKED_IN);
            adjustStuInfo.setStatus(StudentStatusConstant.INTERVIEWING);
            int update = adjustStuInfoMapper.update(adjustStuInfo, updateWrapper);
            if ( update == 1 ) {
                return StudentStatusConstant.INTERVIEWING;
            } else {
                return ResultStatusCodeConstant.FAILED;
            }
        }
    }

    @Override
    public Integer interviewPass(String stuId) {
        QueryWrapper<AdjustStuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        AdjustStuInfo adjustStuInfo = getOne(queryWrapper);
        if ( adjustStuInfo == null ) {
            return 0;
        } else {
            UpdateWrapper<AdjustStuInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id", stuId);
            updateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
            adjustStuInfo.setStatus(StudentStatusConstant.PASS);
            int update = adjustStuInfoMapper.update(adjustStuInfo, updateWrapper);
            if ( update == 1 ) {
                return StudentStatusConstant.PASS;
            } else {
                return ResultStatusCodeConstant.FAILED;
            }
        }
    }
}
