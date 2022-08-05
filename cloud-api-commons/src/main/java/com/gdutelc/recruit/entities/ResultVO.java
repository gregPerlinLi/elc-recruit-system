package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用于和前端进行数据交互
 *
 * @author aaa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    @Getter
    private Integer code;

    @Getter
    private String message;

    @Getter
    private T data;

    /**
     * 错误状况返回前端方法
     *
     * @param code 错误码
     * @param message 错误消息
     */
    public ResultVO(Integer code, String message) {
        this(code, message, null);
    }

}
