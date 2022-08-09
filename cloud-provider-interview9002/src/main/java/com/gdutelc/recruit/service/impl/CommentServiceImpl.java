package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.mapper.CommentMapper;
import com.gdutelc.recruit.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对学生的评价 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
