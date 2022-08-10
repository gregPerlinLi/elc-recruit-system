package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统异常
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
public class ServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServerException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
