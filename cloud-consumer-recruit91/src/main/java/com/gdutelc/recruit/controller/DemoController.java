package com.gdutelc.recruit.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import com.gdutelc.recruit.utils.ResultStatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 示例接口
 *
 * @author gregPerlinLi
 * @date 2022-08-07
 */
@RestController("/demo")
public class DemoController {

    @Resource
    private IInterviewService interviewService;

    @GetMapping(value = "/test/{text}")
    @SentinelResource(value = "test", blockHandler = "handlerException")
    @ApiOperation(value = "测试", tags = "test")
    public ResultVO<String> test(@ApiParam(value = "测试", required = true) @PathVariable("text") String text) {
        return interviewService.test(text);
    }

    public ResultVO<String> handlerException(@PathVariable("text") String text, BlockException exception) {
        return new ResultVO<>(ResultStatusCode.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }
}
