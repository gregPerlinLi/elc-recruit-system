package com.gdutelc.recruit.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("学生报名信息")
public class ApplyInfoDTO {

    /**
     * 微信id
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "微信 ID")
    private String openid;

    /**
     * 学号
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "学号")
    private String stuId;

    /**
     * 姓名
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 自我简介
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "自我简介")
    private String profile;

    /**
     * 能力
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "能力")
    private String skill;

    /**
     * 已加入的社团
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "已加入的社团")
    private String hasJoin;

    /**
     * 第一志愿部门
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "第一志愿部门")
    private Integer firstDept;

    /**
     * 第二志愿部门
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "第二志愿部门")
    private Integer secondDept;

    /**
     * 专业
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "专业")
    private String major;

    /**
     * 学院
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "学院")
    private Integer college;

    /**
     * 班级
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "班级")
    private String clazz;

    /**
     * 在哪里了解电协
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "在哪里了解电协")
    private String whereFind;

}
