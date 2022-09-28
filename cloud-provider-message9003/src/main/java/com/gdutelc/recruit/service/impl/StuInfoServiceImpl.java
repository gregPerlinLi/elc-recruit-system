package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.stereotype.Service;

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
    @Override
    public String getOpenidByStuId(String stuId) {
        QueryWrapper<StuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        StuInfo stuInfo = this.getOne(queryWrapper);
        if ( stuInfo == null ) {
            return null;
        }
        return stuInfo.getOpenid();
    }
}
