package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 学生简要信息实体类<br/>
 * 简要信息包含学号、姓名、性别、第一意向部门、学院、当前面试状态
 *
 * @author aaa
 * @date 2022-8-5
 */
@Data
@AllArgsConstructor
public class BriefInfo {

    /**
     * 学号
     */
    @Getter
    @Setter
    private String stuId;

    /**
     * 姓名
     */
    @Getter
    @Setter
    private String name;

    /**
     * 性别
     */
    @Getter
    @Setter
    private Integer gender;

    /**
     * 第一志愿部门
     */
    @Getter
    @Setter
    private Integer firstDept;

    /**
     * 学院
     */
    @Getter
    @Setter
    private Integer college;

    /**
     * 当前面试状态
     */
    @Getter
    @Setter
    private Integer status;

}
