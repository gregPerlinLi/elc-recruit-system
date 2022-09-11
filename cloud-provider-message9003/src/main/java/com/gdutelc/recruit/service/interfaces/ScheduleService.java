package com.gdutelc.recruit.service.interfaces;


/**
 * @author cherry_jerry
 * @date 2022/08/30 17:29
 */
public interface ScheduleService {
    /**
     * 定时刷新微信服务端接口调用凭据access_token
     */
    void freshAccessTokenSchedule();
}
