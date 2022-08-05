package com.gdutelc.recruit.entities.wx;


import lombok.*;

/**
 * 小程序登录信息实体类
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    @Getter
    @Setter
    private String session_key;

    @Getter
    @Setter
    private String unionid;

    @Getter
    @Setter
    private String errmsg;

    @Getter
    @Setter
    private String openid;

    @Getter
    @Setter
    private Integer errcode;


}
