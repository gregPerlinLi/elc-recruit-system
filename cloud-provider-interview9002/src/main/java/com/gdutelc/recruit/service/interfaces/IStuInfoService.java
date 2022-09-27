package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.SignInDto;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;

import java.util.List;

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

    /**
     * 开始面试
     *
     * @param stuId 开始面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return 学生当前状态码（{@code 450}代表学生第一志愿部门和面试官所在部门不一致，{@code 0}代表不存在此学生，{@code 444}代表由于学生状态不符合要求，请求失败）
     */
    Integer interviewStart(String stuId, String interviewerUsername);

    /**
     * 通过面试
     *
     * @param stuId 通过面试的学生学号
     * @param interviewerUsername 面试官用户名
     * @return 学生当前状态码（{@code 450}代表学生第一志愿部门和面试官所在部门不一致，{@code 0}代表不存在此学生，{@code 444}代表由于学生状态不符合要求，请求失败）
     */
    Integer interviewPass(String stuId, String interviewerUsername);


    ResultVO<List<SignInDto>> getSignInList(int deptId) throws JsonProcessingException;

}
