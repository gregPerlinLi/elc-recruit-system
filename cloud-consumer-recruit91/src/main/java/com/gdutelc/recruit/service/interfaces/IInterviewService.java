package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 面试模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Service
@FeignClient(name = "cloud-provider-interview", contextId = "InterviewServiceApi")
public interface IInterviewService {

    /**
     * 示例接口
     *
     * @param text 随便一个值，如果为空返回错误
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    @GetMapping(value = "/pro/demo/test/{text}")
    ResultVO<String> test(@PathVariable("text") String text);
}
