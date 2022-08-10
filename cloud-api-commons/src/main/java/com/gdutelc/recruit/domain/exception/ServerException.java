package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统异常
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@NoArgsConstructor
public class ServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    public ServerException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServerException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }
}
