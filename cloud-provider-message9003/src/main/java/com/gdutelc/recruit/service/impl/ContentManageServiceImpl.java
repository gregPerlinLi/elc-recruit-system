package com.gdutelc.recruit.service.impl;

import com.gdutelc.recruit.enums.Usage;
import com.gdutelc.recruit.service.interfaces.ContentManageService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author cherry_jerry
 * @date 2022/10/03 13:44
 */
@Service
public class ContentManageServiceImpl implements ContentManageService {

    @Value("${wechat-notify.model.first-interview.id}")
    private String firstInterViewModelId;
    @Value("${wechat-notify.model.first-interview.fields}")
    private String[] firstInterViewModelFields;
    @Value("${wechat-notify.model.first-interview.contents}")
    private String[] firstInterViewModelContents;

    @Value("${wechat-notify.model.first-interview-passed.id}")
    private String firstInterViewPassedModelId;
    @Value("${wechat-notify.model.first-interview-passed.fields}")
    private String[] firstInterViewPassedModelFields;
    @Value("${wechat-notify.model.first-interview-passed.contents}")
    private String[] firstInterViewPassedModelContents;

    @Value("${wechat-notify.model.first-interview-failed.id}")
    private String firstInterViewFailedModelId;
    @Value("${wechat-notify.model.first-interview-failed.fields}")
    private String[] firstInterViewFailedModelFields;
    @Value("${wechat-notify.model.first-interview-failed.contents}")
    private String[] firstInterViewFailedModelContents;

    @Value("${wechat-notify.model.written-test.id}")
    private String writtenTestModelId;
    @Value("${wechat-notify.model.written-test.fields}")
    private String[] writtenTestModelFields;
    @Value("${wechat-notify.model.written-test.contents}")
    private String[] writtenTestModelContents;

    @Value("${wechat-notify.model.second-interview.id}")
    private String secondInterviewModelId;
    @Value("${wechat-notify.model.second-interview.fields}")
    private String[] secondInterviewModelFields;
    @Value("${wechat-notify.model.second-interview.contents}")
    private String[] secondInterviewModelContents;

    @Value("${wechat-notify.model.finally-passed.id}")
    private String finallyPassedModelId;
    @Value("${wechat-notify.model.finally-passed.fields}")
    private String[] finallyPassedModelFields;
    @Value("${wechat-notify.model.finally-passed.contents}")
    private String[] finallyPassedModelContents;

    @Value("${wechat-notify.model.finally-failed.id}")
    private String finallyFailedModelId;
    @Value("${wechat-notify.model.finally-failed.fields}")
    private String[] finallyFailedModelFields;
    @Value("${wechat-notify.model.finally-failed.contents}")
    private String[] finallyFailedModelContents;

    @Value("${wechat-notify.model.apply-success.id}")
    private String applySuccessModelId;
    @Value("${wechat-notify.model.apply-success.fields}")
    private String[] applySuccessModelFields;
    @Value("${wechat-notify.model.apply-success.contents}")
    private String[] applySuccessModelContents;

    @Value("${wechat-notify.model.sign-in-success.id}")
    private String signInSuccessModelId;
    @Value("${wechat-notify.model.sign-in-success.fields}")
    private String[] signInSuccessModelFields;
    @Value("${wechat-notify.model.sign-in-success.contents}")
    private String[] signInSuccessModelContents;


    @Value("${wechat-notify.model.interview-start.id}")
    private String interviewStartModelId;
    @Value("${wechat-notify.model.interview-start.fields}")
    private String[] interviewStartModelFields;
    @Value("${wechat-notify.model.interview-start.contents}")
    private String[] interviewStartModelContents;

    @Override
    public Map<String, Object> getPackedNotifyData(Usage usage) {
        String[] fields;
        String[] contents;
        switch (usage) {
            case FIRST_INTERVIEW -> {
                fields = firstInterViewModelFields;
                contents = firstInterViewModelContents;
            }
            case FIRST_PASSED -> {
                fields = firstInterViewPassedModelFields;
                contents = firstInterViewPassedModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case FIRST_FAILED -> {
                fields = firstInterViewFailedModelFields;
                contents = firstInterViewFailedModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case WRITTEN_TEST -> {
                fields = writtenTestModelFields;
                contents = writtenTestModelContents;
            }
            case SECOND_INTERVIEW -> {
                fields = secondInterviewModelFields;
                contents = secondInterviewModelContents;
            }
            case FINALLY_PASSED -> {
                fields = finallyPassedModelFields;
                contents = finallyPassedModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case FINALLY_FAILED -> {
                fields = finallyFailedModelFields;
                contents = finallyFailedModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case APPLY_SUCCESS -> {
                fields = applySuccessModelFields;
                contents = applySuccessModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case SIGN_IN_SUCCESS -> {
                fields = signInSuccessModelFields;
                contents = signInSuccessModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            case INTERVIEW_START -> {
                fields = interviewStartModelFields;
                contents = interviewStartModelContents;
                contents[2] = GenericUtils.getFullTimeStr();
            }
            default -> {
                // ?_?
                fields = new String[0];
                contents = new String[0];
            }
        }
        return setNotifyData(fields,contents);
    }

    @Override
    public String getNotifyModelId(Usage usage) {
        return switch (usage) {
            case FIRST_INTERVIEW -> firstInterViewModelId;
            case FIRST_PASSED -> firstInterViewPassedModelId;
            case FIRST_FAILED -> firstInterViewFailedModelId;
            case WRITTEN_TEST -> writtenTestModelId;
            case SECOND_INTERVIEW -> secondInterviewModelId;
            case FINALLY_PASSED -> finallyPassedModelId;
            case FINALLY_FAILED -> finallyFailedModelId;
            case APPLY_SUCCESS -> applySuccessModelId;
            case SIGN_IN_SUCCESS -> signInSuccessModelId;
            case INTERVIEW_START -> interviewStartModelId;
        };
    }

    private Map<String, Object> setNotifyData(String[] fields, String[] contents){
        Map<String, Object> data = new HashMap<>(16);
        LinkedList<Map<String, Object>> contentList = new LinkedList<>();

        Map<String, Object> entry;
        for(String content : contents){
            entry = new HashMap<>(2);
            entry.put("value",content);
            contentList.add(entry);
        }

        for(String field :fields){
            data.put(field,contentList.removeFirst());
        }

        return  data;
    }

    @Override
    public String[] getNotifyFields(Usage usage) {
        return null;
    }

    @Override
    public String[] getNotifyContents(Usage usage) {
        return null;
    }
}
