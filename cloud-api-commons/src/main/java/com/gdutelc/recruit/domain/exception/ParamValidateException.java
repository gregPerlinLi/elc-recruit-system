package com.gdutelc.recruit.domain.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * 参数校验异常
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@NoArgsConstructor
public class ParamValidateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    public ParamValidateException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ParamValidateException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }
}
