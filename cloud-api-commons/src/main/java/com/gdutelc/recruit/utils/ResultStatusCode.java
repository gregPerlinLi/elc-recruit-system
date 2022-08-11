package com.gdutelc.recruit.utils;

/**
 * ResultVO中的状态码接口
 *
 * @author TufSolareyes
 * @date 2022-08-05
 */
public interface ResultStatusCode {

    /**
     * 请求成功
     */
    int SUCCESS = 200;


    /**
     * 处理结果为空
     */
    int NOT_FIND = 204;

    /**
     * 无权限访问
     */
    int FORBIDDEN = 403;

    /**
     * 请求过多，被限流
     */
    int TO_MANY_REQUEST = 429;

    /**
     * 参数校验异常（参数不合法）
     */
    int PARAM_VALIDATE_EXCEPTION = 450;

    /**
     * 处理过程报错
     */
    int SERVER_ERROR = 500;

    /**
     * 业务异常
     */
    int BUSINESS_EXCEPTION = 550;

    /**
     * 微服务间请求连接超时
     */
    int SERVER_TIME_OUT = 599;
}
