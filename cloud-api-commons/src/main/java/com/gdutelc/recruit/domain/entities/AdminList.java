package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;

/**
 * 管理员列表
 *
 * @author gregPerlinLi
 * @date 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@TableName("admin_list")
public class AdminList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;


}
