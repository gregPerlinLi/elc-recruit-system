package com.gdutelc.recruit.utils;

/**
 * 招新总体进度状态码接口
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
public interface RecruitStatusCode {
    /**
     * 报名
     */
    int APPLY = 10;

    /**
     * 一面
     */
    int FIRST_INTERVIEW = 20;

    /**
     * 二面
     */
    int SECOND_INTERVIEW = 30;

    /**
     * 结束
     */
    int END = 40;
}
