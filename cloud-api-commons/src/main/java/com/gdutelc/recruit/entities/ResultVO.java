package com.gdutelc.recruit.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用于和前端进行数据交互
 *
 * @author gregperlinli
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通用返回消息体")
public class ResultVO<T> {

    /**
     * 消息代码
     */
    @Getter
    @ApiModelProperty(value = "消息代码")
    private Integer code;

    /**
     * 返回信息
     */
    @Getter
    @ApiModelProperty(value = "返回信息")
    private String msg;

    /**
     * 返回前端需要获取的实体对象
     */
    @Getter
    @ApiModelProperty(value = "返回实体对象")
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