package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/30 13:42
 */
@RestController
@RequestMapping("/pro/super_admin/elc_access/")
public class WeChatMessageController {

    @Resource
    IPassListService iPassListService;
    @Resource
    WeChatServerService weChatServerService;

    @GetMapping(value = "/first_interview_notify")
    public ResultVO<List<String>> firstInterviewNotify(){
        List<StuInfo> firstInterviewList = iPassListService.getOpenIdList(StudentStatusConstant.REGISTERED);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendFirstInterviewNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有一面提醒发送成功",successSendingList);
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分一面提醒发送成功",successSendingList);
        }
    }


    @GetMapping(value = "/second_interview_notify")
    public ResultVO<List<String>> secondInterviewNotify(){
        List<StuInfo> firstInterviewList = iPassListService.getOpenIdList(StudentStatusConstant.PASS);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendSecondInterviewNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有二面提醒发送成功",successSendingList);
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分二面提醒发送成功",successSendingList);
        }
    }

    @GetMapping(value = "/second_interview_notify")
    public ResultVO<List<String>> writtenTestNotify(){
        List<StuInfo> firstInterviewList = iPassListService.getOpenIdList(StudentStatusConstant.PASS);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendWrittenTestNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有笔试提醒发送成功",successSendingList);
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分笔试提醒发送成功",successSendingList);
        }
    }
}
