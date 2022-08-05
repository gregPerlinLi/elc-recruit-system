package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 学生简要信息实体类
 *
 * 简要信息包含学号、姓名、性别、第一意向部门、学院
 */
@Data
@AllArgsConstructor
public class Brief_Info {

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
    private Integer first_dept;

    @Getter
    @Setter
    private Integer college;


}
