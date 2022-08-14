package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 消息推送模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
@Service
@FeignClient(name = "cloud-provider-message", contextId = "MessageServiceApi")
public interface IMessageService {

    /**
     * 生产者微信登录接口
     *
     * @param jsCode 微信登录code
     * @param grantType 授权类型
     * @return {@link ResultVO}，其中数据为小程序登录信息
     */
    @GetMapping(value = "/pro/elc_public/wx_login/{js_code}/{grant_type}")
    ResultVO<LoginInfo> wxLogin(@PathVariable("js_code") String jsCode, @PathVariable("grant_type") String grantType);
}
