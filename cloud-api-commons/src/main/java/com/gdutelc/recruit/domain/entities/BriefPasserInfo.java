package com.gdutelc.recruit.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cherry_jerry
 * @date 2022/08/28 15:23
 */
@Data
@TableName("stu_info")
public class BriefPasserInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer gender;

    private String major;

    private String stuId;

    private String phone;
}
