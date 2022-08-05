package com.gdutelc.recruit.entities;

/**
 * ResultVO中的状态码接口
 */
public interface ResultStatusCode {

    /**
     * 成功
     */
    int SUCCESS = 200;

    /**
     * 前端传的参数有问题
     */
    int PARA_EXPECTION = 202;

    /**
     * 处理结果为空
     */
    int EXPECTION = 204;

    /**
     * 处理过程报错
     */
    int ERROR = 500;
}
