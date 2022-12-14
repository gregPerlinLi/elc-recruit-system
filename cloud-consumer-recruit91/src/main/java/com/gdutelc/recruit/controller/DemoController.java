package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.SentinelBlockHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 示例接口
 *
 * @author gregPerlinLi
 * @date 2022-08-07
 */
@RestController
@RequestMapping(value = "/recruit/elc_access")
public class DemoController {

    @Resource
    private IInterviewService interviewService;

    @GetMapping(value = "/test/{text}")
    @SentinelResource(value = "test", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "handlerException")
    @ApiOperation(value = "测试", tags = "test")
    public ResultVO<String> test(@ApiParam(value = "测试", required = true) @PathVariable("text") String text) {
        return interviewService.test(text);
    }

}
