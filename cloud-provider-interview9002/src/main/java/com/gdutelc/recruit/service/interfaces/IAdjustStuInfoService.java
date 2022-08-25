package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;

/**
 * <p>
 * 调剂学生名单 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface IAdjustStuInfoService extends IService<AdjustStuInfo> {

    /**
     * 设置面试者二面调剂
     *
     * @param stuId 需要调剂的学生学号
     * @param department 学生想要调剂的部门
     * @param interviewerUsername 面试官用户名
     * @return 学生调剂部门代码（{@code 450}代表学生第一志愿部门和面试官所在部门不一致，{@code 0}代表不存在此学生，{@code 444}代表由于学生状态不符合要求，请求失败）
     */
    Integer adjust(String stuId, Integer department, String interviewerUsername);

    /**
     * 开始调剂面试
     *
     * @param stuId 开始面试的学生学号
     * @return 学生当前状态码（{@code 450}代表学生第一志愿部门和面试官所在部门不一致，{@code 0}代表不存在此学生，{@code 444}代表由于学生状态不符合要求，请求失败）
     */
    Integer interviewStart(String stuId);

    /**
     * 通过调剂面试
     *
     * @param stuId 通过面试的学生学号
     * @return 学生当前状态码（{@code 450}代表学生第一志愿部门和面试官所在部门不一致，{@code 0}代表不存在此学生，{@code 444}代表由于学生状态不符合要求，请求失败）
     */
    Integer interviewPass(String stuId);
}
