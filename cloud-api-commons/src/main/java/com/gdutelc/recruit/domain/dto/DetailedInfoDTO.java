package com.gdutelc.recruit.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gdutelc.recruit.domain.entities.StuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 学生详细信息
 *
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生详细信息")
@TableName("stu_info")
public class DetailedInfoDTO {

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

    /**
     * {@link StuInfo}类与{@link DetailedInfoDTO}的转换构造方法
     * @param stuInfo 需要转换的实体类
     */
    public DetailedInfoDTO (StuInfo stuInfo) {
        this.stuId = stuInfo.getStuId();
        this.name = stuInfo.getName();
        this.gender = stuInfo.getGender();
        this.profile = stuInfo.getProfile();
        this.skill = stuInfo.getSkill();
        this.hasJoin = stuInfo.getHasJoin();
        this.firstDept = stuInfo.getFirstDept();
        this.secondDept = stuInfo.getSecondDept();
        this.major = stuInfo.getMajor();
        this.college = stuInfo.getCollege();
        this.clazz = stuInfo.getClazz();
        this.whereFind = stuInfo.getWhereFind();
    }
}
