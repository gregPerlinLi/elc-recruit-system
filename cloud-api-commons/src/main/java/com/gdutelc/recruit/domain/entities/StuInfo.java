package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("stu_info")
public class StuInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String stuId;

    private String name;

    private Integer gender;

    private String profile;

    private String skill;

    private String hasJoin;

    private Integer firstDept;

    private Integer secondDept;

    private String major;

    private Integer college;

    private String clazz;

    private String whereFind;

    private Integer status;


}
