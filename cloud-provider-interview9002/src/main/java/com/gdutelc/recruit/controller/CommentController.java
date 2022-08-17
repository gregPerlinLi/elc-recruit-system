package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.impl.CommentServiceImpl;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TUFSolareyes
 * @date 22/08/18
 */

@RestController
@RequestMapping("/pro/interview/elc_access")
public class CommentController {

    @Resource
    private ICommentService iCommentService;

    @PostMapping("/publish_comment.post")
    @ResponseBody
    public ResultVO addComment(Comment comment) throws IllegalAccessException {
        return iCommentService.addComment(comment);
    }
}
