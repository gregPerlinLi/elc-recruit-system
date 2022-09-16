package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;

/**
 * 学生简要信息 服务类
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
public interface IBriefInfoService {

    /**
     * 获取报名者简要报名信息集合
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @param department 部门筛选（0为全选）
     * @param stuStatusCode 面试状态筛选（20为全选）
     * @return {@link Page} 报名者简要信息分页集合
     */
    PageDTO<BriefInfoDTO> briefApplyQuery(Integer page, Integer limit, Integer department, Integer stuStatusCode);

    /**
     * 根据姓名<b>模糊</b>查询学生集合
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link Page} 要查询的报名者简要信息分页集合
     */
    PageDTO<BriefInfoDTO> searchStuByName(String name, Integer page, Integer limit);

    /**
     * 根据学号<b>模糊</b>查询学生集合
     *
     * @param stuId 模糊查询的学号
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link Page} 要查询的报名者简要信息分页集合
     */
    PageDTO<BriefInfoDTO> searchStuByStuId(String stuId, Integer page, Integer limit);
}
