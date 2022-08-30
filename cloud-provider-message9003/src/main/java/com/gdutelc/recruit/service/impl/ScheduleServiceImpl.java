package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.service.interfaces.ScheduleService;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cherry_jerry
 * @date 2022/08/30 17:32
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    WeChatServerService weChatServerService;

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void freshAccessTokenSchedule() {
        weChatServerService.refreshAccessToken();
    }
}
