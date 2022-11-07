package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 通过学生学号获取其openid
     *
     * @param stuId 需要获取openid的学生学号
     * @return openid，如果返回空则代表无此学生
     */
    String getOpenidByStuId(String stuId);


    /**
     * 将学生的状态从失败设置为一面失败
     *
     * @param openId 需要获取openid
     * @return openid，如果返回空则代表无此学生
     */
    String setFailedAtFirstStatusByOpenId(String openId);

}
