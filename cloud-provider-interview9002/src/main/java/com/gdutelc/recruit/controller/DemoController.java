package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.exception.BusinessException;
import com.gdutelc.recruit.domain.exception.ParamValidateException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import org.springframework.web.bind.annotation.*;

/**
 * 生产者示例接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@RestController
@RequestMapping("/pro/demo")
public class DemoController {

    @GetMapping(value = "/test/{text}")
    public ResultVO<String> test(@PathVariable("text") String text) {
        if ( "".equals(text) ) {
            return new ResultVO(444, "请输入内容");
        } else if ( "+".equals(text) ) {
            throw new ParamValidateException(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "PARAM ERROR!");
        } else if ( "$".equals(text) ) {
            throw new BusinessException(ResultStatusCodeConstant.BUSINESS_EXCEPTION, "BUSINESS EXCEPTION");
        } else {
            return new ResultVO<>(200, "success", String.valueOf(Integer.parseInt(text) / Integer.parseInt(text)));
        }
    }
}
