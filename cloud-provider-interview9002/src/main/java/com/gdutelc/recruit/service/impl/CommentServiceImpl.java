package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.CommentDTO;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.CommentMapper;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private final static Integer MAX_MARK = 5;

    public static final Integer MIN_MARK = 0;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public ResultVO addComment(CommentDTO commentDTO) throws IllegalAccessException {
        if(!GenericUtils.ofNullable(commentDTO)||!GenericUtils.allOfNullable(commentDTO)){
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误");
        }
        if ( commentDTO.getMark() > MAX_MARK || commentDTO.getMark() < MIN_MARK ) {
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "评分应该在0~5之间");
        }
        Comment comment = new Comment(commentDTO);
        int insert = commentMapper.insert(comment);
        if(insert == 1){
            return new ResultVO(ResultStatusCodeConstant.SUCCESS,"发布成功");
        }
        return new ResultVO(ResultStatusCodeConstant.NOT_FIND,"插入失败");

    }

    @Override
    public ResultVO<List<CommentDTO>> queryComment(String stuId) {
        if( !GenericUtils.ofNullable(stuId) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "参数有误");
        }
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        if ( !GenericUtils.ofNullable(comments) || comments.size() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "没有关于此学生的评价");
        }
        List<CommentDTO> commentDTOList = new ArrayList<>(comments.size());
        for ( Comment comment : comments ) {
            CommentDTO commentDTO = new CommentDTO(comment);
            commentDTOList.add(commentDTO);
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "查询到该学生的" + comments.size() + "条评价", commentDTOList);
    }
}
