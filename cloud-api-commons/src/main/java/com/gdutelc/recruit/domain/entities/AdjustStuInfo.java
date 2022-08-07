package com.gdutelc.recruit.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调剂学生名单
 *
 * @author gregPerlinLi
 * @date 2022-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdjustStuInfo {
    Long id;
    String openid;
    String stuId;
    String name;
    Integer adjustDept;
    String major;
    Integer college;
    Integer status;
}
