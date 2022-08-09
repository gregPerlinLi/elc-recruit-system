package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.entities.Process;
import com.gdutelc.recruit.mapper.ProcessMapper;
import com.gdutelc.recruit.service.IProcessService;
import org.springframework.stereotype.Service;

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

}
