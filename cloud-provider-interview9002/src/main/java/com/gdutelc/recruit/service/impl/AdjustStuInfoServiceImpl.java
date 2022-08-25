package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.AdjustStuInfoMapper;
import com.gdutelc.recruit.mapper.InterviewerListMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
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

    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    InterviewerListMapper interviewerListMapper;

    @Override
    public Integer adjust(String stuId, String interviewerUsername) {
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("stuId", stuId);
        StuInfo stuInfo = stuInfoMapper.selectOne(studentQueryWrapper);
        interviewerQueryWrapper.eq("username", interviewerUsername);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerQueryWrapper);
        if ( stuInfo == null ) {
            return 0;
        } else if ( !stuInfo.getFirstDept().equals(interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        } else {
            AdjustStuInfo adjustStuInfo = new AdjustStuInfo(stuInfo);
            adjustStuInfo.setStatus(StudentStatusConstant.CHECKED_IN);
            stuInfo.setStatus(StudentStatusConstant.CHECKED_IN);
            UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
            studentUpdateWrapper.eq("stuId", stuId);
            studentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
            int insertAdjustStuInfo = adjustStuInfoMapper.insert(adjustStuInfo);
            int updateStuInfo = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
            if ( insertAdjustStuInfo == 1 && updateStuInfo == 1 ) {
                return stuInfo.getSecondDept();
            } else {
                return ResultStatusCodeConstant.FAILED;
            }
        }
    }

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
