package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 录取学生列表
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-28
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@TableName("admission_stu")
public class AdmissionStu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuId;

    private String name;

    private Integer gender;

    private Integer college;

    private String major;

    private String clazz;

    private String phone;

    private Integer admissionDept;

    /**
     * {@link StuInfo} 与 {@link AdmissionStu} 的转换构造方法
     *
     * @param stuInfo 需要转换的 {@link StuInfo} 实体类
     */
    public AdmissionStu( StuInfo stuInfo ) {
        this.stuId = stuInfo.getStuId();
        this.name = stuInfo.getName();
        this.gender = stuInfo.getGender();
        this.college = stuInfo.getCollege();
        this.major = stuInfo.getMajor();
        this.clazz = stuInfo.getClazz();
        this.phone = stuInfo.getPhone();
        this.admissionDept = stuInfo.getFirstDept();
    }

    /**
     * {@link StuInfo} 与 {@link AdmissionStu} 的转换构造方法 (其中录取部门自定义)
     * @param stuInfo 需要转换的 {@link StuInfo} 实体类
     * @param adjustDept 录取部门
     */
    public AdmissionStu( StuInfo stuInfo, Integer adjustDept ) {
        this.stuId = stuInfo.getStuId();
        this.name = stuInfo.getName();
        this.gender = stuInfo.getGender();
        this.college = stuInfo.getCollege();
        this.major = stuInfo.getMajor();
        this.clazz = stuInfo.getClazz();
        this.phone = stuInfo.getPhone();
        this.admissionDept = adjustDept;
    }
}
