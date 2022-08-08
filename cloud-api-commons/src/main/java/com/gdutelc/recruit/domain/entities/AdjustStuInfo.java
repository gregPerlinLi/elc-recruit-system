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


}
