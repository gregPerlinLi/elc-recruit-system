package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * @author TUFSolareyes
 * @date 22/10/03
 */
public interface IGetNameList {

    /**
     * 获取报名名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    ResultVO<byte[]> getApplyList(Integer deptId);

    /**
     * 获取第一次面试通过名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    ResultVO<byte[]> getFirstPassList(Integer deptId);

    /**
     * 获取第二次面试通过名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    ResultVO<byte[]> getSecondPassList(Integer deptId);

    /**
     * 获取第二次面试调剂名单
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    ResultVO<byte[]> getSecondAdjustList(Integer deptId);
}
