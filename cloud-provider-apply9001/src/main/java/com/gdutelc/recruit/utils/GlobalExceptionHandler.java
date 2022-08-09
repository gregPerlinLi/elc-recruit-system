package com.gdutelc.recruit.utils;

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
    public ResultVO expectionHandler(Exception e){
        String msg = e.getMessage();
        if (e instanceof NumberFormatException){
            return new ResultVO(ResultStatusCode.PARAM_EXCEPTION,msg,null);
        }
        return new ResultVO(ResultStatusCode.SERVER_ERROR,msg,null);
    }
}
