package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.config.NotifyContentConfig;
import com.gdutelc.recruit.enums.Usage;
import com.gdutelc.recruit.service.interfaces.ContentManageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author cherry_jerry
 * @date 2022/10/03 13:44
 */
@Service
public class ContentManageServiceImpl implements ContentManageService {

    @Value("${wechat-notify.model.first-interview}")
    private NotifyContentConfig.FirstInterviewModel firstInterViewModel;
    @Value("${wechat-notify.model.written-test}")
    private NotifyContentConfig.WrittenTestModel writtenTestModel;
    @Value("${wechat-notify.model.second-interview}")
    private NotifyContentConfig.SecondInterviewModel secondInterviewModel;
    @Value("${wechat-notify.model.finally-passed}")
    private NotifyContentConfig.FinallyPassedModel finallyPassedModel;
    @Value("${wechat-notify.model.apply-success}")
    private NotifyContentConfig.ApplySuccessModel applySuccessModel;
    @Value("${wechat-notify.model.sign-in-success}")
    private NotifyContentConfig.SignInSuccessModel signInSuccessModel;
    @Value("${wechat-notify.model.interview-start}")
    private NotifyContentConfig.InterviewStartModel interviewStartModel;

    @Override
    public Map<String, Object> getPackedModel(Usage usage) {
        return null;
    }

    @Override
    public String getNotifyModelId(Usage usage) {
        return switch (usage) {
            case FIRST_INTERVIEW -> firstInterViewModel.getId();
            case WRITTEN_TEST -> writtenTestModel.getId();
            case SECOND_INTERVIEW -> secondInterviewModel.getId();
            case FINALLY_PASSED -> finallyPassedModel.getId();
            case APPLY_SUCCESS -> applySuccessModel.getId();
            case SIGN_IN_SUCCESS -> signInSuccessModel.getId();
            case INTERVIEW_START -> interviewStartModel.getId();
        };
    }

    @Override
    public String[] getNotifyFields(Usage usage) {
        return switch (usage) {
            case FIRST_INTERVIEW -> firstInterViewModel.getFields();
            case WRITTEN_TEST -> writtenTestModel.getFields();
            case SECOND_INTERVIEW -> secondInterviewModel.getFields();
            case FINALLY_PASSED -> finallyPassedModel.getFields();
            case APPLY_SUCCESS -> applySuccessModel.getFields();
            case SIGN_IN_SUCCESS -> signInSuccessModel.getFields();
            case INTERVIEW_START -> interviewStartModel.getFields();
        };
    }

    @Override
    public String[] getNotifyContents(Usage usage) {
        return switch (usage) {
            case FIRST_INTERVIEW -> firstInterViewModel.getContents();
            case WRITTEN_TEST -> writtenTestModel.getContents();
            case SECOND_INTERVIEW -> secondInterviewModel.getContents();
            case FINALLY_PASSED -> finallyPassedModel.getContents();
            case APPLY_SUCCESS -> applySuccessModel.getContents();
            case SIGN_IN_SUCCESS -> signInSuccessModel.getContents();
            case INTERVIEW_START -> interviewStartModel.getContents();
        };
    }
}
