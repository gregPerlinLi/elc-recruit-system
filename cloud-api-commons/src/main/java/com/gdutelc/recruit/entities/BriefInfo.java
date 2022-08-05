package com.gdutelc.recruit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 学生简要信息实体类<br/>
 * 简要信息包含学号、姓名、性别、第一意向部门、学院
 *
 * @author aaa
 */
@Data
@AllArgsConstructor
public class BriefInfo {

    @Getter
    @Setter
    private String stuId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer gender;

    @Getter
    @Setter
    private Integer firstDept;

    @Getter
    @Setter
    private Integer college;


}
