package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.wx.AccessTokenDTO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;

import java.util.Map;

/**
 * 提供调用微信服务端接口的服务
 * @author cherry_jerry
 * @date 2022/08/29 13:35
 */

public interface WeChatServerService {
    /**
     * 向微信服务端发送请求，获取小程序全局唯一后台接口调用凭据
     * 该方法会导致旧的凭据失效，且缓存新的凭据
     * @return 微信服务端返回的数据对象
     */
    AccessTokenDTO refreshAccessToken();

    /**
     * 获取微信服务端接口调用凭据
     * @return 小程序全局唯一后台接口调用凭据access_token
     */
    String getAccessToken();

    /**
     * 向用户发送订阅消息
     * @param toUser 接收者的openid
     * @param templateId 消息模板id，在微信小程序后台管理中设置
     * @param data 模板内容，格式为键值对
     * @return 请求返回信息
     */
    SendMessageDTO sendSubscribeMessage(String toUser, String templateId, Map<String,Object> data);

    /**
     * 向用户发送第一次面试提醒
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendFirstInterviewNotify(String toUser);

    /**
     * 向用户发送一面通过提醒
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendFirstInterviewPassedNotify(String toUser);

    /**
     * 向用户发送一面未通过提醒
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendFirstInterviewFailedNotify(String toUser);

    /**
     * 向用户发送笔试提醒
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendWrittenTestNotify(String toUser);

    /**
     * 向用户发送第二次面试提醒
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendSecondInterviewNotify(String toUser);

    /**
     * 向用户发送最终通过面试提醒
     * @author TUFSolareyes
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendFinallyPassedNotify(String toUser);

    /**
     * 向用户发送最终未通过提醒
     * @author TUFSolareyes
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendFinallyFailedNotify(String toUser);


    /**
     * 向用户发送报名成功消息
     *
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendApplySuccessNotify(String toUser);

    /**
     * 向用户发送签到成功消息
     *
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendSignInSuccessNotify(String toUser);

    /**
     * 向用户发送面试开始通知
     *
     * @param toUser 接收者的openid
     * @return 请求返回信息
     */
    SendMessageDTO sendInterviewStartNotify(String toUser);
}
