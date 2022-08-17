package com.gdutelc.recruit.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 分页实体
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@AllArgsConstructor
@ApiModel("分页实体")
@Data
public class PageDTO<T> {
    /**
     * 总条数
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "总条数")
    private Long total;
    /**
     * 当前页数据
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "当前页面数据")
    private List<T> list;

}
