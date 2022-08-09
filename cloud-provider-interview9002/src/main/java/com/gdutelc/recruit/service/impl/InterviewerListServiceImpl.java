package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.mapper.InterviewerListMapper;
import com.gdutelc.recruit.service.interfaces.IInterviewerListService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 面试官列表 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class InterviewerListServiceImpl extends ServiceImpl<InterviewerListMapper, InterviewerList> implements IInterviewerListService {

}
