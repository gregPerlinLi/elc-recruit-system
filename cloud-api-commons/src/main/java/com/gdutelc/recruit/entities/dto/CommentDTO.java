package com.gdutelc.recruit.entities.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 面试评价
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@ApiModel("面试评价 实体类")
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
}
