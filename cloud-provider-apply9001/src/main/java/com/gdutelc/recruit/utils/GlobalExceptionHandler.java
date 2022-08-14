package com.gdutelc.recruit.utils;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.exception.ParamValidateException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author TUFSolareyes
 * @date 22/08/09
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(Exception e){
        String msg = e.getMessage();
        if (e instanceof ParamValidateException){
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,msg,null);
        }
        return new ResultVO(ResultStatusCodeConstant.SERVER_ERROR,msg,null);
    }
}
