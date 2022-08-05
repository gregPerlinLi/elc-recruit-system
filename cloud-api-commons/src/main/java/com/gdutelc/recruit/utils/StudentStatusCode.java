package com.gdutelc.recruit.utils;

/**
 * 学生面试状态码接口
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
public interface StudentStatusCode {
    /**
     * 已注册
     */
    int REGISTERED = 0;

    /**
     * 已签到
     */
    int CHECKED_IN = 1;

    /**
     * 面试中
     */
    int INTERVIEWING = 2;

    /**
     * 通过
     */
    int PASS = 3;

    /**
     * 录用
     */
    int EMPLOYMENT = 10;

    /**
     * 失败
     */
    int FAILED = -1;
}
