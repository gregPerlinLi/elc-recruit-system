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
     * 前端传的参数有问题
     */
    int PARAM_EXCEPTION = 202;

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
     * 处理过程报错
     */
    int SERVER_ERROR = 500;

    /**
     * 微服务间请求连接超时
     */
    int SERVER_TIME_OUT = 599;
}
