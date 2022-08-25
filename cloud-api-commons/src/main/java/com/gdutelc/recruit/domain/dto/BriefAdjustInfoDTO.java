package com.gdutelc.recruit.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;
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
@ApiModel("调剂学生简要信息")
@TableName("adjust_stu_info")
public class BriefAdjustInfoDTO {

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
     * 调剂部门
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "调剂愿部门")
    private Integer adjustDept;

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
     * {@link StuInfo}类与{@link BriefAdjustInfoDTO}的转换构造方法
     * @param adjustStuInfo 需要转换的实体类
     */
    public BriefAdjustInfoDTO(AdjustStuInfo adjustStuInfo) {
        this.stuId = adjustStuInfo.getStuId();
        this.name = adjustStuInfo.getName();
        this.adjustDept = adjustStuInfo.getAdjustDept();
        this.college = adjustStuInfo.getCollege();
        this.status = adjustStuInfo.getStatus();
    }

}
