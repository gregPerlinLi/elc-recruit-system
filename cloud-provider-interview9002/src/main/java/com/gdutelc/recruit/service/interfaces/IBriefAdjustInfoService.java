package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdutelc.recruit.domain.dto.BriefAdjustInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;

/**
 * 学生简要信息 服务类
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
public interface IBriefAdjustInfoService {

    /**
     * 获取调剂报名者简要报名信息集合
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link Page} 调剂报名者简要信息分页集合
     */
    PageDTO<BriefAdjustInfoDTO> briefAdjustApplyQuery(Integer page, Integer limit);

    /**
     * 根据姓名<b>模糊</b>查询调剂学生集合
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link Page} 要查询的调剂报名者简要信息分页集合
     */
    PageDTO<BriefAdjustInfoDTO> searchAdjustStuByName(String name, Integer page, Integer limit);

}
