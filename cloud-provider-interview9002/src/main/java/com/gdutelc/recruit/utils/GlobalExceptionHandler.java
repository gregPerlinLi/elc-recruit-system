package com.gdutelc.recruit.utils;

import com.gdutelc.recruit.domain.exception.BusinessException;
import com.gdutelc.recruit.domain.exception.ParamValidateException;
import com.gdutelc.recruit.domain.exception.ServerException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author gregPerlinLi
 * @date 22/08/09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  捕获业务异常
     */
    @ExceptionHandler(value = {BusinessException.class})
    public ResultVO processBusinessException(HttpServletResponse response, BusinessException exception) {
        log.error(exception.getMsg(), exception);
        // 设置HTTP状态码
        response.setStatus(ResultStatusCode.BUSINESS_EXCEPTION);
        response.setContentType("application/json;charset=UTF-8");
        return new ResultVO(exception.getCode(), exception.getMsg());
    }

    /**
     * 捕获系统异常
     */
    @ExceptionHandler(value = {Exception.class, ServerException.class})
    public ResultVO processServerException(HttpServletResponse response, Exception exception) {
        log.error(exception.getMessage(), exception);
        // 设置HTTP状态码
        response.setStatus(ResultStatusCode.SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        return new ResultVO(ResultStatusCode.SERVER_ERROR, exception.getMessage());
    }

    /**
     * 捕获参数校验异常
     */
    @ExceptionHandler(value = {ParamValidateException.class, MethodArgumentNotValidException.class})
    public ResultVO handleException(HttpServletResponse response, Exception exception) {
        log.error(exception.getMessage(), exception);
        // 设置HTTP状态码
        response.setStatus(ResultStatusCode.PARAM_VALIDATE_EXCEPTION);
        response.setContentType("application/json;charset=UTF-8");
        return new ResultVO(ResultStatusCode.PARAM_VALIDATE_EXCEPTION, exception.getMessage());
    }
}
