package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 面试官列表
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@TableName("interviewer_list")
public class InterviewerList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Integer dept;


}
