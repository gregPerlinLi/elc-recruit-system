package com.gdutelc.recruit.constant;

/**
 * 小程序流程图接口
 * @author TUFSolareyes
 * @date 22/09/18
 */
public interface TaroStudentStatusConstant {

    /**
     * 正在招新阶段
     */
    int IN_APPLY = 0;

    /**
     * 一面
     */
    int FIRST_INTERVIEW = 1;

    /**
     * 笔试
     */
    int WRITTEN_EXAM = 2;

    /**
     * 二面
     */
    int SECOND_INTERVIEW = 3;

    /**
     * 已经G了
     */
    int FAILED = -1;

    /**
     * 结束
     */
    int END = 3;

    /**
     * 签到成功
     */
    int SIGNIN_SUCCESS = 1;

    /**
     * 签到失败
     */
    int SIGNIN_FAILED = 0;
}
