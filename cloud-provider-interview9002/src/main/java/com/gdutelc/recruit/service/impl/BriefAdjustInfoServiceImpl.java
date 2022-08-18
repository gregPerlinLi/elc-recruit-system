package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.dto.BriefAdjustInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
import com.gdutelc.recruit.mapper.BriefAdjustInfoMapper;
import com.gdutelc.recruit.service.interfaces.IBriefAdjustInfoService;
import org.springframework.stereotype.Service;

/**
 * 学生简要信息 服务实现类
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@Service
public class BriefAdjustInfoServiceImpl extends ServiceImpl<BriefAdjustInfoMapper, BriefAdjustInfoDTO> implements IBriefAdjustInfoService {

    @Override
    public PageDTO<BriefAdjustInfoDTO> briefAdjustApplyQuery(Integer page, Integer limit) {
        Page<BriefAdjustInfoDTO> briefAdjustInfoPage = new Page<>(page, limit);
        Page<BriefAdjustInfoDTO> resultPage;
        resultPage = page(briefAdjustInfoPage);
        PageDTO<BriefAdjustInfoDTO> objectPageDTO = new PageDTO<>();
        objectPageDTO.setTotal(resultPage.getTotal());
        objectPageDTO.setList(resultPage.getRecords());
        return objectPageDTO;
    }
}
