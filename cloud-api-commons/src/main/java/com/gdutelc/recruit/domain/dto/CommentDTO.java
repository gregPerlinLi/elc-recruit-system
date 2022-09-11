package com.gdutelc.recruit.domain.dto;


import com.gdutelc.recruit.domain.entities.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 面试评价
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("面试评价")
public class CommentDTO {

    /**
     * 学号
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "学号")
    private String stuId;

    /**
     * 打分 (1~5)
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "打分 (1~5)")
    private Integer mark;

    /**
     * 评价
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "评价")
    private String comment;

    /**
     * 面试官
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "面试官")
    private String interviewer;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    /**
     * {@link Comment} 转 {@link CommentDTO}
     *
     * @param comment 需要转换的{@link Comment}实体
     */
    public CommentDTO(Comment comment) {
        this.stuId = comment.getStuId();
        this.mark = comment.getMark();
        this.comment = comment.getComment();
        this.interviewer = comment.getInterviewer();
    }
}
