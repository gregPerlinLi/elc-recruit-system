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
    @Resource
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"??????????????????????????????");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"??????????????????????????????");
        }
    }


    @GetMapping(value = "/first_interview_result_notify")
    public ResultVO<Void> firstInterviewResultNotify(){
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        if(currentProgress == RecruitStatusConstant.FIRST_INTERVIEW) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED,"?????????????????????");
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"??????????????????????????????");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"??????????????????????????????");
        }
    }


    @GetMapping(value = "/second_interview_notify")
    public ResultVO<Void> secondInterviewNotify(){
        List<StuInfo> firstInterviewList = iPassListService.getOpenIdList(StudentStatusConstant.REGISTERED);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendSecondInterviewNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"??????????????????????????????");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"??????????????????????????????");
        }
    }

    @GetMapping(value = "/written_test_notify")
    public ResultVO<Void> writtenTestNotify(){
        List<StuInfo> firstInterviewList = iPassListService.getOpenIdList(StudentStatusConstant.REGISTERED);
        List<String> successSendingList = new LinkedList<>();
        for (StuInfo stuInfo :
                firstInterviewList) {
            SendMessageDTO sendMessageDTO = weChatServerService.sendWrittenTestNotify(stuInfo.getOpenid());
            if(sendMessageDTO.getErrCode() == 0){
                successSendingList.add(stuInfo.getName());
            }
        }
        if(successSendingList.size() == firstInterviewList.size()){
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"??????????????????????????????");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"??????????????????????????????");
        }
    }


    @GetMapping(value = "/second_interview_result_notify")
    public ResultVO<Void> secondInterviewResultNotify(){
        String currentProgressStr = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer currentProgress = Integer.parseInt(currentProgressStr);
        if(currentProgress != RecruitStatusConstant.END) {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED,"?????????????????????");
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
            return new ResultVO<> (ResultStatusCodeConstant.SUCCESS,"??????????????????????????????");
        }else {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"??????????????????????????????");
        }
    }


    /**
     * ????????????????????????
     *
     * @param openid ????????????????????????openid
     * @return {@link ResultVO}??????????????????????????????????????????????????????
     */
    @GetMapping(value = "/apply_success_notify/{openid}")
    public ResultVO<Void> applySuccessNotify(@PathVariable("openid") String openid) {
        SendMessageDTO sendMessageDTO = weChatServerService.sendApplySuccessNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "??????????????????");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "???????????????????????????: " + sendMessageDTO.getErrMsg());
        }
    }

    /**
     * ????????????????????????
     *
     * @param openid ????????????????????????openid
     * @return {@link ResultVO}??????????????????????????????????????????????????????
     */
    @GetMapping(value = "/sign_in_success_notify/{openid}")
    public ResultVO<Void> signInSuccessNotify(@PathVariable("openid") String openid) {
        SendMessageDTO sendMessageDTO = weChatServerService.sendSignInSuccessNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "??????????????????");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "???????????????????????????: " + sendMessageDTO.getErrMsg());
        }
    }

    /**
     * ????????????????????????
     *
     * @param stuId ??????????????????????????????
     * @return {@link ResultVO}??????????????????????????????????????????????????????
     */
    @GetMapping(value = "/interview_start_notify/{stu_id}")
    public ResultVO<Void> interviewStartNotify(@PathVariable("stu_id") String stuId) {
        String openid = stuInfoService.getOpenidByStuId(stuId);
        SendMessageDTO sendMessageDTO = weChatServerService.sendInterviewStartNotify(openid);
        if ( sendMessageDTO.getErrCode() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "??????????????????");
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "???????????????????????????: " + sendMessageDTO.getErrMsg());
        }
    }


    @GetMapping(value = "/over_all_progress")
    public ResultVO<Integer> overAllProgress() {
        return iOverAllProgress.overAllProgress();
    }
}
