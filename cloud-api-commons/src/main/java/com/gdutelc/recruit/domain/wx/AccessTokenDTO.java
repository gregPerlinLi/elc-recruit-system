package com.gdutelc.recruit.domain.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取小程序调用凭证请求返回的数据对象
 * @author cherry_jerry
 * @date 2022/08/29 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}
