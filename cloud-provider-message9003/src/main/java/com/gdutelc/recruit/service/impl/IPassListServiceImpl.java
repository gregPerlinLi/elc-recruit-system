package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.mapper.BriefPasserInfoMapper;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/28 22:04
 */
@Service
public class IPassListServiceImpl implements IPassListService {

    @Resource
    BriefPasserInfoMapper briefPasserInfoMapper;

    @Override
    public List<BriefPasserInfo> getFirstInterviewPassList(Integer department) {
        QueryWrapper<BriefPasserInfo> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","gender","gender","major","stu_id","phone")
                .eq("first_dept",department)
                .eq("status", StudentStatusConstant.PASS);
        return briefPasserInfoMapper.selectList(wrapper);
    }

    @Override
    public List<BriefPasserInfo> getFinalAdmissionList(Integer department) {
        //@TODO 等待修改二面业务逻辑
        return null;
    }
}
