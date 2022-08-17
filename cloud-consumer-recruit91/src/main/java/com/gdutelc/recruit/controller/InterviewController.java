package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.service.interfaces.IInterviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 面试相关接口
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/interview/elc_access")
public class InterviewController {

    @Resource
    IInterviewService interviewService;


}
