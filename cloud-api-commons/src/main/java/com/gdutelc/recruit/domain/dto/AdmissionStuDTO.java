package com.gdutelc.recruit.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gdutelc.recruit.domain.entities.AdmissionStu;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 最终录取学生信息
 *
 * @author gregPerlinLi
 * @date 2022-08-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("最终录取学生信息")
@TableName("admission_stu")
public class AdmissionStuDTO {

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 学院
     */
    private Integer college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 录取部门
     */
    private Integer admissionDept;

    /**
     * {@link AdmissionStu} 实例转换为 {@link AdmissionStuDTO} 实例构造方法
     *
     * @param admissionStu 需要转换的 {@link AdmissionStu} 实例
     */
    public AdmissionStuDTO(AdmissionStu admissionStu) {
        this.stuId = admissionStu.getStuId();
        this.name = admissionStu.getName();
        this.gender = admissionStu.getGender();
        this.college = admissionStu.getCollege();
        this.major = admissionStu.getMajor();
        this.clazz = admissionStu.getClazz();
        this.phone = admissionStu.getPhone();
        this.admissionDept = admissionStu.getAdmissionDept();
    }
}
