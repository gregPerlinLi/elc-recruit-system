package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.CommentMapper;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private CommentMapper commentMapper;

    @Override
    public ResultVO addComment(Comment comment) throws IllegalAccessException {
        if(!GenericUtils.ofNullable(comment)||!GenericUtils.allOfNullable(comment)){
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误");
        }
        if ( comment.getMark() > 5 || comment.getMark() < 0 ) {
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "评分应该在0~5之间");
        }
        int insert = commentMapper.insert(comment);
        if(insert == 1){
            return new ResultVO(ResultStatusCodeConstant.SUCCESS,"发布成功");
        }
        return new ResultVO(ResultStatusCodeConstant.NOT_FIND,"插入失败");

    }

    @Override
    public ResultVO<List<Comment>> queryComment(String stuId) {
        if( !GenericUtils.ofNullable(stuId) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "参数有误");
        }
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        if ( !GenericUtils.ofNullable(comments) || comments.size() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "没有关于此学生的评价");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "查询到该学生的" + comments.size() + "条评价", comments);
    }
}
