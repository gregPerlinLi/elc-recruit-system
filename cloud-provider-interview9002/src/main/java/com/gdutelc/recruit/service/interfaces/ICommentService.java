package com.gdutelc.recruit.service.interfaces;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * <p>
 * 对学生的评价 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 发布评论
     * @author TUFSolareyes
     * @param comment
     * @return
     */
    ResultVO addComment(Comment comment) throws IllegalAccessException;
}
