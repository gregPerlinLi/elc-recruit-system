package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.DeptConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
import com.gdutelc.recruit.mapper.BriefInfoMapper;
import com.gdutelc.recruit.service.interfaces.IBriefInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 学生简要信息 服务实现类
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@Service
public class BriefInfoServiceImpl extends ServiceImpl<BriefInfoMapper, BriefInfoDTO> implements IBriefInfoService {

    @Override
    public PageDTO<BriefInfoDTO> briefApplyQuery(Integer page, Integer limit, Integer department, Integer stuStatusCode) {
        Page<BriefInfoDTO> briefInfoPage = new Page<>(page, limit);
        Page<BriefInfoDTO> resultPage;
        QueryWrapper<BriefInfoDTO> queryWrapper = new QueryWrapper<>();
        if ( department == DeptConstant.ALL && stuStatusCode == StudentStatusConstant.ALL ) {
            resultPage = page(briefInfoPage);
        } else if ( department == DeptConstant.ALL ) {
            queryWrapper.eq("status", stuStatusCode);
            resultPage = page(briefInfoPage, queryWrapper);
        } else if (stuStatusCode == StudentStatusConstant.ALL) {
            queryWrapper.eq("first_dept", department);
            resultPage = page(briefInfoPage, queryWrapper);
        } else {
            HashMap<String, Integer> params = new HashMap<>(2);
            params.put("first_dept", department);
            params.put("status", stuStatusCode);
            queryWrapper.allEq(params);
            resultPage = page(briefInfoPage, queryWrapper);
        }
        PageDTO<BriefInfoDTO> objectPageDTO = new PageDTO<>();
        objectPageDTO.setTotal(resultPage.getTotal());
        objectPageDTO.setList(resultPage.getRecords());
        return objectPageDTO;
    }

    @Override
    public PageDTO<BriefInfoDTO> searchStuByName(String name, Integer page, Integer limit) {
        Page<BriefInfoDTO> briefInfoPage = new Page<>(page, limit);
        QueryWrapper<BriefInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        Page<BriefInfoDTO> resultPage = page(briefInfoPage, queryWrapper);
        PageDTO<BriefInfoDTO> objectPageDTO = new PageDTO<>();
        objectPageDTO.setTotal(resultPage.getTotal());
        objectPageDTO.setList(resultPage.getRecords());
        return objectPageDTO;
    }

    @Override
    public PageDTO<BriefInfoDTO> searchStuByStuId(String stuId, Integer page, Integer limit) {
        Page<BriefInfoDTO> briefInfoPage = new Page<>(page, limit);
        QueryWrapper<BriefInfoDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("stu_id", stuId);
        Page<BriefInfoDTO> resultPage = page(briefInfoPage, queryWrapper);
        PageDTO<BriefInfoDTO> objectPageDTO = new PageDTO<>();
        objectPageDTO.setTotal(resultPage.getTotal());
        objectPageDTO.setList(resultPage.getRecords());
        return objectPageDTO;
    }
}
