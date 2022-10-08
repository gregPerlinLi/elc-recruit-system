package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.enums.Usage;

import java.util.Map;

/**
 * 集中管理消息推送的内容
 * @author cherry_jerry
 * @date 2022/10/03 13:12
 */
public interface ContentManageService {

    /**
     * 返回消息推送数据
     * @param usage 消息推送的使用场景
     * @return 获取
     */
    Map<String,Object> getPackedNotifyData(Usage usage);

    /**
     * 获取模板id
     * @param usage 消息推送的使用场景
     * @return 微信消息推送的模板ID
     */
    String getNotifyModelId(Usage usage);

    /**
     * 获取模板字段
     * @param usage 消息推送的使用场景
     * @return 相应微信模板的字段数组
     */
    String[] getNotifyFields(Usage usage);

    /**
     * 获取模板内容
     * @param usage 消息推送的使用场景
     * @return 相应微信模板的字段数组
     */
    String[] getNotifyContents(Usage usage);

}
