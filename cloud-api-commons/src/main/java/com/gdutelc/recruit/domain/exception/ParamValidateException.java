package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 参数校验异常
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
public class ParamValidateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    public ParamValidateException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ParamValidateException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
