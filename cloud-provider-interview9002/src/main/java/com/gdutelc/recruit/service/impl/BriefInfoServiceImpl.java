package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.DeptConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.mapper.BriefInfoMapper;
import com.gdutelc.recruit.service.interfaces.IBriefInfoService;

import java.util.HashMap;

/**
 * 学生简要信息 服务实现类
 * @author gregPerlinLi
 * @date 2022-08-17
 */
public class BriefInfoServiceImpl extends ServiceImpl<BriefInfoMapper, BriefInfoDTO> implements IBriefInfoService {

    @Override
    public Page<BriefInfoDTO> briefApplyQuery(Integer page, Integer limit, Integer department, Integer stuStatusCode) {
        Page<BriefInfoDTO> briefInfoPage = new Page<>(page, limit);
        Page<BriefInfoDTO> resultPage;
        QueryWrapper<BriefInfoDTO> queryWrapper = new QueryWrapper<>();
        if ( department == DeptConstant.ALL && stuStatusCode == StudentStatusConstant.ALL ) {
            resultPage = page(briefInfoPage);
        } else if ( department == DeptConstant.ALL ) {
            queryWrapper.eq("status", stuStatusCode);
            resultPage = page(briefInfoPage, queryWrapper);
        } else if (stuStatusCode == StudentStatusConstant.ALL) {
            queryWrapper.eq("firstDept", department);
            resultPage = page(briefInfoPage, queryWrapper);
        } else {
            HashMap<String, Integer> params = new HashMap<>(2);
            params.put("firstDept", department);
            params.put("status", stuStatusCode);
            queryWrapper.allEq(params);
            resultPage = page(briefInfoPage, queryWrapper);
        }
        PageDTO<BriefInfoDTO> objectPageDTO = new PageDTO<>();
        objectPageDTO.setTotal(resultPage.getTotal());
        objectPageDTO.setPages(resultPage.getPages());
        return objectPageDTO;
    }
}