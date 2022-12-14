package com.gdutelc.recruit.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 学生报名信息
 *
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生报名信息")
@TableName("stu_info")
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
     * 联系电话
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 在哪里了解电协
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "在哪里了解电协")
    private String whereFind;

    @Getter
    @Setter
    @ApiModelProperty(value = "状态，报名时默认为0")
    private Integer status;

}
