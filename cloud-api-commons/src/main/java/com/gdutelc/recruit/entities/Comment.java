package com.gdutelc.recruit.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 面试评价
 */
@Data
@AllArgsConstructor
public class Comment {

    @Getter
    @Setter
    private String stu_id;

    /**
     * 打分
     */
    @Getter
    @Setter
    private Integer mark;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private String interviewer;
}
