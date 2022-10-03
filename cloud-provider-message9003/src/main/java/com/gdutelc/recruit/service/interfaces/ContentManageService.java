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
     * 获取完整消息模型
     * @param usage 消息推送的使用场景
     * @return 消息模型，包括模板ID、字段字符串数组和内容字符串数组
     */
    Map<String,Object> getPackedModel(Usage usage);

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
