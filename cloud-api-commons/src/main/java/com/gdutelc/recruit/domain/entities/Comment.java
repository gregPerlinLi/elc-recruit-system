package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gdutelc.recruit.domain.dto.CommentDTO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 对学生的评价
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuId;

    private Integer mark;

    private String comment;

    private String interviewer;

    /**
     * {@link CommentDTO} 转 {@link Comment}
     * @param commentDTO 需要转换的{@link CommentDTO}实体
     */
    public Comment(CommentDTO commentDTO) {
        this.stuId = commentDTO.getStuId();
        this.mark = commentDTO.getMark();
        this.comment = commentDTO.getComment();
        this.interviewer = commentDTO.getInterviewer();
    }
}
