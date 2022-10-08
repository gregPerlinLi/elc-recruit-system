package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * @author TUFSolareyes
 * @date 22/10/03
 */
public interface IExPeopleList {

    /**
     * 导出报名名单
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    ResultVO exportApplyList();

    /**
     * 导出一面通过名单
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    ResultVO exportFirstPassList();

    /**
     * 导出二面通过名单
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    ResultVO exportSecondPassList();

    /**
     * 导出二面调剂通过名单
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    ResultVO exportSecondAdjustPassList();

    /**
     * 导出最终录取名单
     *
     * @return {@link ResultVO}，其中不包含数据，只包含状态码和信息
     */
    ResultVO exportAdmissionList();
}
