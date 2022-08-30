package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdutelc.recruit.constant.DeptConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.dto.AdmissionStuDTO;
import com.gdutelc.recruit.domain.entities.AdmissionStu;
import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.AdmissionStuMapper;
import com.gdutelc.recruit.mapper.BriefPasserInfoMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/28 22:04
 */
@Service
public class PassListServiceImpl implements IPassListService {

    @Resource
    BriefPasserInfoMapper briefPasserInfoMapper;
    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    AdmissionStuMapper admissionStuMapper;

    @Override
    public List<BriefPasserInfo> getFirstInterviewPassList(Integer department) {
        QueryWrapper<BriefPasserInfo> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","gender","gender","major","stu_id","phone")
                .eq("first_dept",department)
                .eq("status", StudentStatusConstant.PASS);
        return briefPasserInfoMapper.selectList(wrapper);
    }

    @Override
    public List<AdmissionStuDTO> getFinalAdmissionList(Integer department) {
        QueryWrapper<AdmissionStu> queryWrapper = new QueryWrapper<>();
        if ( department == DeptConstant.ALL ) {
            List<AdmissionStu> admissionStuList = admissionStuMapper.selectList(null);
            List<AdmissionStuDTO> admissionStuDTOList = new ArrayList<>(admissionStuList.size());
            for ( AdmissionStu admissionStu : admissionStuList ) {
                AdmissionStuDTO admissionStuDTO = new AdmissionStuDTO(admissionStu);
                admissionStuDTOList.add(admissionStuDTO);
            }
            return admissionStuDTOList;
        }
        queryWrapper.eq("admission_dept", department);
        List<AdmissionStu> admissionStuList = admissionStuMapper.selectList(queryWrapper);
        List<AdmissionStuDTO> admissionStuDTOList = new ArrayList<>(admissionStuList.size());
        for ( AdmissionStu admissionStu : admissionStuList ) {
            AdmissionStuDTO admissionStuDTO = new AdmissionStuDTO(admissionStu);
            admissionStuDTOList.add(admissionStuDTO);
        }
        return admissionStuDTOList;
    }

    @Override
    public List<StuInfo> getOpenIdList(Integer studentStatus) {
        QueryWrapper<StuInfo> wrapper = new QueryWrapper<>();
        wrapper.select("openid","name")
                .eq("status",studentStatus);
        return stuInfoMapper.selectList(wrapper);
    }
}
