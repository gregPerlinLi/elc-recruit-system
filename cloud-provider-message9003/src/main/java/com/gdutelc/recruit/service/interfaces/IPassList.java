package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.entities.BriefPasserInfo;

import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/28 15:04
 */
public interface IPassList {
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
}
