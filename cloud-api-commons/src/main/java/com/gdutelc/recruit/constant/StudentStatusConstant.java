package com.gdutelc.recruit.constant;

/**
 * 学生面试状态码接口
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
public interface StudentStatusConstant {

    /**
     * 全选
     */
    int ALL = 20;

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
     * 调剂
     */
    int ADJUSTED = 3;

    /**
     * 通过
     */
    int PASS = 4;

    /**
     * 录用
     */
    int EMPLOYMENT = 10;

    /**
     * 失败
     */
    int FAILED = -1;
}
