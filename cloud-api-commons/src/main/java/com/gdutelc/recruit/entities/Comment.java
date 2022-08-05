package com.gdutelc.recruit.entities;


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
public class Comment {

    /**
     * 学号
     */
    @Getter
    @Setter
    private String stuId;

    /**
     * 打分 (1~5)
     */
    @Getter
    @Setter
    private Integer mark;

    /**
     * 评价
     */
    @Getter
    @Setter
    private String comment;

    /**
     * 面试官
     */
    @Getter
    @Setter
    private String interviewer;
}
