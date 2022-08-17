package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.entities.StuInfo;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface IStuInfoService extends IService<StuInfo> {

    /**
     * 获取报名者详细信息
     *
     * @param stuId 报名者学号
     * @return {@link DetailedInfoDTO} 报名者详细信息实体类
     */
    DetailedInfoDTO detailedApplyQuery(String stuId);

}
