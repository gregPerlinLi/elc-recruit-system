package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 生产者示例接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Controller
@RequestMapping("/pro/demo")
public class DemoController {

    @GetMapping(value = "/test/{text}")
    @ResponseBody
    public ResultVO<String> test(@PathVariable("text") String text) {
        if ( "".equals(text) ) {
            return new ResultVO(444, "请输入内容");
        } else {
            return new ResultVO<>(200, "success", text);
        }
    }
}
