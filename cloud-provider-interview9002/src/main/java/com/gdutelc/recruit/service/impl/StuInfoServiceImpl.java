package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.InterviewerListMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class StuInfoServiceImpl extends ServiceImpl<StuInfoMapper, StuInfo> implements IStuInfoService {

    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    InterviewerListMapper interviewerListMapper;

    @Override
    public DetailedInfoDTO detailedApplyQuery(String stuId) {
        QueryWrapper<StuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        StuInfo rawResult = getOne(queryWrapper);
        if ( rawResult == null ) {
            return null;
        }
        return new DetailedInfoDTO(rawResult);
    }

    @Override
    public Integer interviewStart(String stuId, String interviewerUsername) {
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("stu_id", stuId);
        StuInfo stuInfo = getOne(studentQueryWrapper);
        interviewerQueryWrapper.eq("username", interviewerUsername);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerQueryWrapper);
        if ( stuInfo == null || interviewerList == null ) {
            return 0;
        }
        if ( !stuInfo.getFirstDept().equals(interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        }
        UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
        studentUpdateWrapper.eq("stu_id", stuId);
        studentUpdateWrapper.eq("status", StudentStatusConstant.CHECKED_IN);
        stuInfo.setStatus(StudentStatusConstant.INTERVIEWING);
        int update = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
        if ( update == 1 ) {
            return StudentStatusConstant.INTERVIEWING;
        } else {
            return ResultStatusCodeConstant.FAILED;
        }
    }

    @Override
    public Integer interviewPass(String stuId, String interviewerUsername) {
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerListQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("stu_id", stuId);
        StuInfo stuInfo = getOne(studentQueryWrapper);
        interviewerListQueryWrapper.eq("username", interviewerUsername);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerListQueryWrapper);
        if ( stuInfo == null || interviewerList == null ) {
            return 0;
        }
        if ( !stuInfo.getFirstDept().equals(interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        } else {
            UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
            studentUpdateWrapper.eq("stu_id", stuId);
            studentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
            stuInfo.setStatus(StudentStatusConstant.PASS);
            int update = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
            if ( update == 1 ) {
                return StudentStatusConstant.PASS;
            } else {
                return ResultStatusCodeConstant.FAILED;
            }
        }
    }
}
