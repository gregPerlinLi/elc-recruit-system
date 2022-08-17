package com.gdutelc.recruit.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gdutelc.recruit.domain.entities.StuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 学生简要信息实体类
 * 简要信息包含学号、姓名、性别、第一意向部门、学院、当前面试状态
 *
 * @author TufSolareyes
 * @date 2022-08-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生简要信息")
@TableName("stu_info")
public class BriefInfoDTO {

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
     * 第一志愿部门
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "第一志愿部门")
    private Integer firstDept;

    /**
     * 学院
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "学院")
    private Integer college;

    /**
     * 当前面试状态
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "当前面试状态")
    private Integer status;

    /**
     * {@link StuInfo}类与{@link BriefInfoDTO}的转换构造方法
     * @param stuInfo 需要转换的实体类
     */
    public BriefInfoDTO(StuInfo stuInfo) {
        this.stuId = stuInfo.getStuId();
        this.name = stuInfo.getName();
        this.gender = stuInfo.getGender();
        this.firstDept = stuInfo.getFirstDept();
        this.college = stuInfo.getCollege();
        this.status = stuInfo.getStatus();
    }

}
