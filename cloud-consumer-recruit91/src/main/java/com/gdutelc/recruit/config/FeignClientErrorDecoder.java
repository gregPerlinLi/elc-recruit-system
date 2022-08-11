package com.gdutelc.recruit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdutelc.recruit.domain.exception.BusinessException;
import com.gdutelc.recruit.domain.exception.ParamValidateException;
import com.gdutelc.recruit.domain.exception.ServerException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.utils.ResultStatusCode;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * OpenFeign 错误解码器
 *
 * @author gregPerlinLi
 * @date 2022-08-10
 */
@Slf4j
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String errorContent = Util.toString(response.body().asReader());
                ObjectMapper mapper = new ObjectMapper();
                if ( response.status() == ResultStatusCode.SERVER_ERROR ) {
                    ResultVO result = mapper.readValue(errorContent, ResultVO.class);
                    return new ServerException(result.getCode(), result.getMsg());
                } else if ( response.status() == ResultStatusCode.PARAM_VALIDATE_EXCEPTION ) {
                    ResultVO result =  mapper.readValue(errorContent, ResultVO.class);
                    return new ParamValidateException(result.getCode(), result.getMsg());
                } else if ( response.status() == ResultStatusCode.BUSINESS_EXCEPTION ) {
                    ResultVO result =  mapper.readValue(errorContent, ResultVO.class);
                    return new BusinessException(result.getCode(), result.getMsg());
                }
            }
        } catch (IOException e) {
            log.error("FeignClientErrorDecoder decode exception: {}.", e);
            return e;
        }
        return new Exception("服务端未知异常！");
    }
}
