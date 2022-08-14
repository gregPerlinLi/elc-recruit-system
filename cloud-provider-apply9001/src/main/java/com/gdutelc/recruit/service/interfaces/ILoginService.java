package com.gdutelc.recruit.service.interfaces;

/**
 * 学生登录服务
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
public interface ILoginService {

    /**
     * 学生登录，openid写入Redis
     *
     * @param openid 微信openid
     * @return 是否成功录入
     * @throws Exception 录入异常
     */
    boolean login(String openid) throws Exception;
}
