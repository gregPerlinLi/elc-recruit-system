package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 业务异常
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
