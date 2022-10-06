package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.*;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;
import com.gdutelc.recruit.domain.entities.AdmissionStu;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.AdjustStuInfoMapper;
import com.gdutelc.recruit.mapper.AdmissionStuMapper;
import com.gdutelc.recruit.mapper.InterviewerListMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IAdjustStuInfoService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Resource
    AdmissionStuMapper admissionStuMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer adjust(String stuId, Integer department, String interviewerUsername) {
        if ( Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS))) != RecruitStatusConstant.SECOND_INTERVIEW ) {
            return ResultStatusCodeConstant.FAILED;
        }
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("stu_id", stuId);
        StuInfo stuInfo = stuInfoMapper.selectOne(studentQueryWrapper);
        interviewerQueryWrapper.eq("username", interviewerUsername);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerQueryWrapper);
        if ( stuInfo == null || interviewerList == null ) {
            return 0;
        }
        if ( stuInfo.getStatus() != StudentStatusConstant.INTERVIEWING ) {
            return ResultStatusCodeConstant.FAILED;
        }
        if ( !stuInfo.getFirstDept().equals(interviewerList.getDept()) && !Objects.equals(DeptConstant.ALL, interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        }
        AdjustStuInfo adjustStuInfo = new AdjustStuInfo(stuInfo);
        if ( department != null ) {
            adjustStuInfo.setAdjustDept(department);
        }
        adjustStuInfo.setStatus(StudentStatusConstant.CHECKED_IN);
        stuInfo.setStatus(StudentStatusConstant.ADJUSTED);
        UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
        studentUpdateWrapper.eq("stu_id", stuId);
        studentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
        int insertAdjustStuInfo = adjustStuInfoMapper.insert(adjustStuInfo);
        int updateStuInfo = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
        System.out.println(insertAdjustStuInfo + " asdfasdf " + updateStuInfo);
        if ( insertAdjustStuInfo == 1 || updateStuInfo == 1 ) {
            return stuInfo.getSecondDept();
        } else {
            return ResultStatusCodeConstant.FAILED;
        }
    }

    @Override
    public Integer interviewStart(String stuId, String interviewerUsername) {
        QueryWrapper<AdjustStuInfo> adjustStudentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerQueryWrapper = new QueryWrapper<>();
        adjustStudentQueryWrapper.eq("stu_id", stuId);
        studentQueryWrapper.eq("stu_id", stuId);
        interviewerQueryWrapper.eq("username", interviewerUsername);
        AdjustStuInfo adjustStuInfo = getOne(adjustStudentQueryWrapper);
        StuInfo stuInfo = stuInfoMapper.selectOne(studentQueryWrapper);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerQueryWrapper);
        if ( adjustStuInfo == null || stuInfo == null || interviewerList == null ) {
            return 0;
        }
        if ( !adjustStuInfo.getAdjustDept().equals(interviewerList.getDept()) && !Objects.equals(DeptConstant.ALL, interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        }
        UpdateWrapper<AdjustStuInfo> adjustStudentUpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
        adjustStudentUpdateWrapper.eq("stu_id", stuId);
        adjustStudentUpdateWrapper.eq("status", StudentStatusConstant.CHECKED_IN);
        studentUpdateWrapper.eq("stu_id", stuId);
        studentUpdateWrapper.eq("status", StudentStatusConstant.ADJUSTED);
        adjustStuInfo.setStatus(StudentStatusConstant.INTERVIEWING);
        stuInfo.setStatus(StudentStatusConstant.INTERVIEWING);
        int updateAdjustStuInfo = adjustStuInfoMapper.update(adjustStuInfo, adjustStudentUpdateWrapper);
        int updateStuInfo = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
        System.out.println(updateAdjustStuInfo + " " + updateStuInfo);
        if ( updateAdjustStuInfo == 1 && updateStuInfo == 1 ) {
            return StudentStatusConstant.INTERVIEWING;
        } else {
            return ResultStatusCodeConstant.FAILED;
        }
    }

    @Override
    public Integer interviewPass(String stuId, String interviewerUsername) {
        QueryWrapper<AdjustStuInfo> adjustStudentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<StuInfo> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<InterviewerList> interviewerQueryWrapper = new QueryWrapper<>();
        adjustStudentQueryWrapper.eq("stu_id", stuId);
        studentQueryWrapper.eq("stu_id", stuId);
        interviewerQueryWrapper.eq("username", interviewerUsername);
        AdjustStuInfo adjustStuInfo = getOne(adjustStudentQueryWrapper);
        StuInfo stuInfo = stuInfoMapper.selectOne(studentQueryWrapper);
        InterviewerList interviewerList = interviewerListMapper.selectOne(interviewerQueryWrapper);
        if ( adjustStuInfo == null || stuInfo == null || interviewerList == null ) {
            return 0;
        }
        if ( !adjustStuInfo.getAdjustDept().equals(interviewerList.getDept()) && !Objects.equals(DeptConstant.ALL, interviewerList.getDept()) ) {
            return ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION;
        }
        QueryWrapper<AdmissionStu> admissionStuQueryWrapper = new QueryWrapper<>();
        admissionStuQueryWrapper.eq("stu_id", stuId);
        if ( admissionStuMapper.exists(admissionStuQueryWrapper) ) {
            // 查看是否已有录取记录，如果已存在录取记录则代表该学生已被主部门录取，此时返回异常
            return ResultStatusCodeConstant.STATUS_EXCEPTION;
        }
        UpdateWrapper<AdjustStuInfo> adjustStudentUpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
        adjustStudentUpdateWrapper.eq("stu_id", stuId);
        adjustStudentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
        studentUpdateWrapper.eq("stu_id", stuId);
        studentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
        adjustStuInfo.setStatus(StudentStatusConstant.PASS);
        stuInfo.setStatus(StudentStatusConstant.PASS);
        int updateAdjustStuInfo = adjustStuInfoMapper.update(adjustStuInfo, adjustStudentUpdateWrapper);
        int updateStuInfo = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
        int insertAdmissionStu = admissionStuMapper.insert(new AdmissionStu(stuInfo, interviewerList.getDept()));
        if ( updateAdjustStuInfo == 1 && updateStuInfo == 1 && insertAdmissionStu == 1 ) {
            return StudentStatusConstant.PASS;
        } else {
            return ResultStatusCodeConstant.FAILED;
        }
    }
}
