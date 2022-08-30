package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.entities.StuInfo;

import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/28 15:04
 */
public interface IPassListService {
    /**
     * 获取一面通过者名单
     * @param department 部门
     * @return {@link BriefPasserInfo} 通过面试者的重要信息：如姓名，学生id，联系电话
     */
    List<BriefPasserInfo> getFirstInterviewPassList(Integer department);

    /**
     * 获取最终录取名单
     * @param department 部门
     * @return {@link BriefPasserInfo} 通过面试者的重要信息：如姓名，学生id，联系电话
     */
    List<BriefPasserInfo> getFinalAdmissionList(Integer department);

    /**
     * 根据学生状态获取学生openid列表
     * @param studentStatus 学生状态类型
     * @return openid和学生姓名
     */
    List<StuInfo> getOpenIdList(Integer studentStatus);

}
