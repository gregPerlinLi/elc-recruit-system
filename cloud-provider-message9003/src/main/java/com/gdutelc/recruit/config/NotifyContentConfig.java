package com.gdutelc.recruit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author cherry_jerry
 * @date 2022/10/03 14:07
 */
@Configuration
public class NotifyContentConfig {
    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.first-interview")
    public static class FirstInterviewModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.written-test")
    public static class WrittenTestModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.second-interview")
    public static class SecondInterviewModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.finally-passed")
    public static class FinallyPassedModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.apply-success")
    public static class ApplySuccessModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.sign-in-success")
    public static class SignInSuccessModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }

    @Data
    @Configuration
    @ConfigurationProperties("wechat-notify.model.interview-start")
    public static class InterviewStartModel {
        private String id;
        private String[] fields;
        private String[] contents;
    }
}
