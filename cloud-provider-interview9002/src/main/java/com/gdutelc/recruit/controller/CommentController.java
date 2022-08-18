package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 面试官评价接口
     *
     * @param comment 评价实体类
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     * @throws IllegalAccessException 非法访问异常
     */
    @PostMapping(value = "/publish_comment")
    public ResultVO addComment(@RequestBody Comment comment) throws IllegalAccessException {
        return iCommentService.addComment(comment);
    }
}
