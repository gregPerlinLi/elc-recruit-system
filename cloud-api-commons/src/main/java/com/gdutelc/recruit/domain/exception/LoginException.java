package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * 登录异常
 *
 * @author gregPerlinLi
 * @date 2022-08-16
 */
@NoArgsConstructor
public class LoginException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    public LoginException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public LoginException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }
}
