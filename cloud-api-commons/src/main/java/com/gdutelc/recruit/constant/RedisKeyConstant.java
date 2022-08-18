package com.gdutelc.recruit.constant;

/**
 * Redis常用key常量接口
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
public interface RedisKeyConstant {

    /**
     * 存放openid集合的Key值
     */
    String STU_OPENID = "stu-openid";

    /**
     * 存放面试总进度的Key值
     */
    String PROCESS = "process";

    /**
     * 存放已登录面试官和管理员的key值
     */
    String LOGIN_USER = "login-user:";

    /**
     * 拼接已登录面试官和管理员的key值
     *
     * @param username 需要拼接的用户名
     * @return 返回拼接后的key值 {@code login-user:username}
     */
    static String loginUserWith(String username) {
        return LOGIN_USER + username;
    }

}
