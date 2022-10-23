package com.gdutelc.recruit.constant;

/**
 * 招新总体进度状态码接口
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
public interface RecruitStatusConstant {
    /**
     * 报名
     */
    int APPLY = 10;

    /**
     * 一面
     */
    int FIRST_INTERVIEW = 20;

    /**
     * 笔试
     */
    int WRITTEN_EXAM = 30;

    /**
     * 二面
     */
    int SECOND_INTERVIEW = 40;

    /**
     * 结束
     */
    int END = 50;

    /**
     * 递增值
     */
    int STEP = 10;
}
