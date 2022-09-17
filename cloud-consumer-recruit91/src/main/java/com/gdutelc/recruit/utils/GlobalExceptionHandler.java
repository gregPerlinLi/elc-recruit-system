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

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultVO<Void> exceptionHandler(HttpServletResponse response, Exception e){
        String msg = e.getMessage();
        if ( e instanceof LoginException ) {
            response.setStatus(ResultStatusCodeConstant.FORBIDDEN);
            return new ResultVO<>(ResultStatusCodeConstant.FORBIDDEN, msg);
        }
        if ( e instanceof ParamValidateException ) {
            response.setStatus(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION);
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, msg);
        }
        if ( e instanceof BusinessException ) {
            response.setStatus(ResultStatusCodeConstant.BUSINESS_EXCEPTION);
            return new ResultVO<>(ResultStatusCodeConstant.BUSINESS_EXCEPTION, msg);
        }
        if ( e instanceof ServerException ) {
            response.setStatus(ResultStatusCodeConstant.SERVER_ERROR);
            return new ResultVO<>(ResultStatusCodeConstant.SERVER_ERROR, msg);
        }
        if (e instanceof RetryableException) {
            response.setStatus(ResultStatusCodeConstant.SERVER_TIME_OUT);
            return new ResultVO<>(ResultStatusCodeConstant.SERVER_TIME_OUT, msg);
        }
        response.setStatus(ResultStatusCodeConstant.SERVER_ERROR);
        return new ResultVO<>(ResultStatusCodeConstant.SERVER_ERROR, msg);
    }
}
