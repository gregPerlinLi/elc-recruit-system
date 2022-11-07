package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.SignInDTO;
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

    /**
     * 获取已签到的学生集合
     *
     * @param deptId 筛选的部门代码
     * @return {@link ResultVO}，其中数据为当前的所有以前到学生的集合
     * @throws JsonProcessingException JSON处理异常
     */
    ResultVO<List<SignInDTO>> getSignInList(int deptId) throws JsonProcessingException;

    /**
     * 获取各个部门的人数情况
     *
     * @return {@link ResultVO}，其中数据为各部门的人数情况
     */
    ResultVO<List<Integer>> getDeptPeopleCount();

    /**
     * 获取学生的状态统计
     *
     * @return {@link ResultVO}，其中数据为当前学生的状态统计
     */
    ResultVO<List<Long>> getStatusPeopleCount();

}
