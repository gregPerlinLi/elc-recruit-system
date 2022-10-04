package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * @author TUFSolareyes
 * @date 22/10/03
 */
public interface IExPeopleList {

    /**
     * 导出报名名单
     * @return
     */
    ResultVO exportApplyList();

    /**
     * 导出一面通过名单
     * @return
     */
    ResultVO exportFirstPassList();

    /**
     * 导出二面通过名单
     * @return
     */
    ResultVO exportSecondPassList();

    /**
     * 导出二面调剂通过名单
     * @return
     */
    ResultVO exportSecondAdjustPassList();

    ResultVO exportAdmissionList();
}
