package com.gdutelc.recruit.utils;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.exception.BusinessException;
import com.gdutelc.recruit.domain.exception.LoginException;
import com.gdutelc.recruit.domain.exception.ParamValidateException;
import com.gdutelc.recruit.domain.exception.ServerException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import feign.RetryableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(Exception e){
        String msg = e.getMessage();
        if ( e instanceof LoginException ) {
            return new ResultVO(ResultStatusCodeConstant.FORBIDDEN, msg);
        }
        if ( e instanceof ParamValidateException ) {
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, msg);
        }
        if ( e instanceof BusinessException ) {
            return new ResultVO(ResultStatusCodeConstant.BUSINESS_EXCEPTION, msg);
        }
        if ( e instanceof ServerException ) {
            return new ResultVO(ResultStatusCodeConstant.SERVER_ERROR, msg);
        }
        if (e instanceof RetryableException) {
            return new ResultVO(ResultStatusCodeConstant.SERVER_TIME_OUT, msg);
        }
        return new ResultVO(ResultStatusCodeConstant.SERVER_ERROR, msg);
    }
}
