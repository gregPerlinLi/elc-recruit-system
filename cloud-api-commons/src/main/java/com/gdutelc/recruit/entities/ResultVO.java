package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用于和前端进行数据交互
 *
 * @author gregperlinli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    /**
     * 消息代码
     */
    @Getter
    private Integer code;

    /**
     * 返回信息
     */
    @Getter
    private String msg;

    /**
     * 返回前端需要获取的实体对象
     */
    @Getter
    private T data;

    /**
     * 返回错误结果方法
     *
     * @param code 错误代码
     * @param message 错误信息
     */
    public ResultVO(Integer code, String message) {
        this(code, message, null);
    }
}
