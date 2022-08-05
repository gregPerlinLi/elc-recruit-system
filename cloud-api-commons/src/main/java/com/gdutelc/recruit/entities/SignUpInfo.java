package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 学生报名信息
 *
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
public class SignUpInfo {

    /**
     * 微信id
     */
    @Getter
    @Setter
    private String openid;

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
     * 自我简介
     */
    @Getter
    @Setter
    private String profile;

    /**
     * 能力
     */
    @Getter
    @Setter
    private String skill;

    /**
     * 已加入的社团
     */
    @Getter
    @Setter
    private String hasJoin;

    /**
     * 第一志愿部门
     */
    @Getter
    @Setter
    private Integer firstDept;

    /**
     * 第二志愿部门
     */
    @Getter
    @Setter
    private Integer secondDept;

    /**
     * 专业
     */
    @Getter
    @Setter
    private String major;

    /**
     * 学院
     */
    @Getter
    @Setter
    private Integer college;

    /**
     * 班级
     */
    @Getter
    @Setter
    private String clazz;

    /**
     * 在哪里了解电协
     */
    @Getter
    @Setter
    private String whereFind;


}
