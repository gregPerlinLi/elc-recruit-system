package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.RecruitStatusConstant;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.entities.AdmissionStu;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.AdmissionStuMapper;
import com.gdutelc.recruit.mapper.InterviewerListMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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
    StringRedisTemplate stringRedisTemplate;

    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    InterviewerListMapper interviewerListMapper;

    @Resource
    AdmissionStuMapper admissionStuMapper;

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
        }
        UpdateWrapper<StuInfo> studentUpdateWrapper = new UpdateWrapper<>();
        studentUpdateWrapper.eq("stu_id", stuId);
        studentUpdateWrapper.eq("status", StudentStatusConstant.INTERVIEWING);
        stuInfo.setStatus(StudentStatusConstant.PASS);
        int update = stuInfoMapper.update(stuInfo, studentUpdateWrapper);
        if ( update == 1 ) {
            if ( Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS))) == RecruitStatusConstant.SECOND_INTERVIEW ) {
                // 此为二面最终录取
                AdmissionStu admissionStu = new AdmissionStu(stuInfo);
                QueryWrapper<AdmissionStu> admissionStuQueryWrapper = new QueryWrapper<>();
                admissionStuQueryWrapper.eq("stu_id", stuId);
                if ( admissionStuMapper.exists(admissionStuQueryWrapper) ) {
                    // 如果发现录取列表中由此学生，则大概率是调剂录取，这种情况下可以直接覆盖原有的录取记录（因为主部门录取优先于调剂部门录取）
                    UpdateWrapper<AdmissionStu> admissionStuUpdateWrapper = new UpdateWrapper<>();
                    admissionStuUpdateWrapper.eq("stu_id", stuId);
                    int updateResult = admissionStuMapper.update(admissionStu, admissionStuUpdateWrapper);
                    if ( updateResult == 1 ) {
                        return StudentStatusConstant.PASS;
                    } else {
                        return ResultStatusCodeConstant.FAILED;
                    }
                }
                int insertResult = admissionStuMapper.insert(admissionStu);
                if ( insertResult == 1 ) {
                    return StudentStatusConstant.PASS;
                } else {
                    return ResultStatusCodeConstant.FAILED;
                }
            }
            return StudentStatusConstant.PASS;
        } else {
            return ResultStatusCodeConstant.FAILED;
        }
    }
}
