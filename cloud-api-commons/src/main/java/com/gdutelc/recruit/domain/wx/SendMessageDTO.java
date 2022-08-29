package com.gdutelc.recruit.domain.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求微信服务端向用户发送订阅信息后返回的数据对象
 * @author cherry_jerry
 * @date 2022/08/29 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageDTO {
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}
