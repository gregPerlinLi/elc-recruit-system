package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;

import java.util.List;

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
     * @param comment 评价实体
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     * @throws IllegalAccessException 非法访问异常
     */
    ResultVO addComment(Comment comment) throws IllegalAccessException;

    /**
     * 查询学生的所有评价
     *
     * @param stuId 需要查询的学生学号
     * @return {@link ResultVO}，其中数据为该报名者的评价集合
     * @author gregPerlinLi
     */
    ResultVO<List<Comment>> queryComment(String stuId);
}
