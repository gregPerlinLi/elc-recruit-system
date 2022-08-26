package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.domain.entities.Process;
import com.gdutelc.recruit.mapper.ProcessMapper;
import com.gdutelc.recruit.service.interfaces.IProcessService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 面试整体进度 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements IProcessService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer getNowProcess() {
        return Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS)));
    }
}
