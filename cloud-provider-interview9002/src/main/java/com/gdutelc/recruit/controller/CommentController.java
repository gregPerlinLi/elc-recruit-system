package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TUFSolareyes
 * @date 22/08/18
 */
@RestController
@RequestMapping("/pro/interview/elc_access")
public class CommentController {

    @Resource
    private ICommentService commentService;

    /**
     * 面试官评价接口
     *
     * @param comment 评价实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     * @throws IllegalAccessException 非法访问异常
     */
    @PostMapping(value = "/publish_comment")
    public ResultVO addComment(@RequestBody Comment comment) throws IllegalAccessException {
        return commentService.addComment(comment);
    }

    /**
     * 查询学生的所有评价
     *
     * @param stuId 需要查询的学生学号
     * @return {@link ResultVO}，其中数据为该报名者的评价集合
     */
    @PostMapping(value = "/query_comments/{stu_id}")
    public ResultVO<List<Comment>> queryComments(@PathVariable("stu_id") String stuId) {
        return commentService.queryComment(stuId);
    }
}
