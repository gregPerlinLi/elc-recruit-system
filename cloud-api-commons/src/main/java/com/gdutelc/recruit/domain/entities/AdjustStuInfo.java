package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 调剂学生名单
 *
 * @author gregPerlinLi
 * @date 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@TableName("adjust_stu_info")
public class AdjustStuInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String stuId;

    private String name;

    private Integer adjustDept;

    private String major;

    private Integer college;

    private Integer status;

    /**
     * {@link StuInfo} 与 {@link AdjustStuInfo} 的转换构造方法
     *
     * @param stuInfo 需要转换的 {@link StuInfo} 实体类
     */
    public AdjustStuInfo(StuInfo stuInfo) {
        this.openid = stuInfo.getOpenid();
        this.stuId = stuInfo.getStuId();
        this.name = stuInfo.getName();
        this.adjustDept = stuInfo.getSecondDept();
        this.major = stuInfo.getMajor();
        this.college = stuInfo.getCollege();
    }
}
