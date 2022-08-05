package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 学生详细信息
 */
@Data
@AllArgsConstructor
public class DetailedInfo {

    @Getter
    @Setter
    private String stu_id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer gender;

    @Getter
    @Setter
    private String profile;

    @Getter
    @Setter
    private String skill;

    @Getter
    @Setter
    private String has_join;

    @Getter
    @Setter
    private Integer first_dept;

    @Getter
    @Setter
    private Integer second_dept;

    @Getter
    @Setter
    private String major;

    @Getter
    @Setter
    private Integer college;

    @Getter
    @Setter
    private String clazz;
}
