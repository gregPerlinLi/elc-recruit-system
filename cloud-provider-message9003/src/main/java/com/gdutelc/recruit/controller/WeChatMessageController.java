package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.RecruitStatusConstant;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.constant.StudentStatusConstant;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.SendMessageDTO;
import com.gdutelc.recruit.service.interfaces.IOverAllProgress;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import com.gdutelc.recruit.service.interfaces.WeChatServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/pro/super_admin/elc_access")
public class WeChatMessageController {

    @Resource
    IPassListService iPassListService;
    @Resource
    WeChatServerService weChatServerService;
    @Resource
    IOverAllProgress iOverAllProgress;
    @Resource
    IStuInfoService stuInfoService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/first_interview_notify")
    public ResultVO<Void> firstInterviewNotify(){
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有一面提醒发送成功");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分一面提醒发送成功");
        }
    }


    @GetMapping(value = "/first_interview_result_notify")
    public ResultVO<Void> firstInterviewResultNotify(){
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        if(currentProgress == RecruitStatusConstant.FIRST_INTERVIEW) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        List<StuInfo> firstInterviewPassedList = iPassListService.getOpenIdList(StudentStatusConstant.REGISTERED);
        List<StuInfo> firstInterviewFailedList = iPassListService.getOpenIdList(StudentStatusConstant.FAILED);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewPassedList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendFirstInterviewPassedNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        for (StuInfo stuInfo :
                firstInterviewFailedList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendFirstInterviewFailedNotify(stuInfo.getOpenid());
            stuInfoService.setFailedAtFirstStatusByOpenId(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewPassedList.size()+firstInterviewFailedList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有一面结果发送成功");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分一面结果发送成功");
        }
    }


    @GetMapping(value = "/second_interview_notify")
    public ResultVO<Void> secondInterviewNotify(){
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有二面提醒发送成功");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分二面提醒发送成功");
        }
    }

    @GetMapping(value = "/written_test_notify")
    public ResultVO<Void> writtenTestNotify(){
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有笔试提醒发送成功");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分笔试提醒发送成功");
        }
    }


    @GetMapping(value = "/second_interview_result_notify")
    public ResultVO<Void> secondInterviewResultNotify(){
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        if(currentProgress == RecruitStatusConstant.SECOND_INTERVIEW) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        List<StuInfo> secondInterviewPassedList = iPassListService.getOpenIdList(StudentStatusConstant.EMPLOYMENT);
        List<StuInfo> secondInterviewFailedList = iPassListService.getOpenIdList(StudentStatusConstant.FAILED);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                secondInterviewPassedList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendFinallyPassedNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        for (StuInfo stuInfo :
                secondInterviewFailedList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendFinallyFailedNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == secondInterviewPassedList.size()+secondInterviewFailedList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"所有终面结果发送成功");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"部分终面结果发送成功");
        }
    }


    /**
     * 发送报名成功消息
     *
     * @param openid 需要发送给的学生openid
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/apply_success_notify/{openid}")
    public ResultVO<Void> applySuccessNotify(@PathVariable("openid") String openid) {
        SendMessageDTO sendMessageDTO = weChatServerService.sendApplySuccessNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "消息发送成功");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "消息发送失败，原因: " + sendMessageDTO.getErrMsg());
        }
    }

    /**
     * 发送签到成功消息
     *
     * @param openid 需要发送给的学生openid
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/sign_in_success_notify/{openid}")
    public ResultVO<Void> signInSuccessNotify(@PathVariable("openid") String openid) {
        SendMessageDTO sendMessageDTO = weChatServerService.sendSignInSuccessNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "消息发送成功");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "消息发送失败，原因: " + sendMessageDTO.getErrMsg());
        }
    }

    /**
     * 发送开始面试消息
     *
     * @param stuId 需要发送给的学生学号
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/interview_start_notify/{stu_id}")
    public ResultVO<Void> interviewStartNotify(@PathVariable("stu_id") String stuId) {
        String openid = stuInfoService.getOpenidByStuId(stuId);
        SendMessageDTO sendMessageDTO = weChatServerService.sendInterviewStartNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "消息发送成功");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "消息发送失败，原因: " + sendMessageDTO.getErrMsg());
        }
    }


    @GetMapping(value = "/over_all_progress")
    public ResultVO<Integer> overAllProgress() {
        return iOverAllProgress.overAllProgress();
    }
}
