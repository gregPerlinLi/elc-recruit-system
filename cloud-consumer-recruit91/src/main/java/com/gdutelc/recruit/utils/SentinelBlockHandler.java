package com.gdutelc.recruit.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * Sentinel限流处理方法
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
public class SentinelBlockHandler {
    public static ResultVO<Void> flowLimitException(BlockException exception) {
        return new ResultVO<>(ResultStatusCodeConstant.TO_MANY_REQUEST, exception.getClass().getCanonicalName() + "\t REQUEST BLOCKED BY SENTINEL ...");
    }
}
