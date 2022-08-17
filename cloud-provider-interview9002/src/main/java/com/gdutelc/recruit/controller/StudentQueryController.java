package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.domain.dto.BriefInfoDTO;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.dto.PageDTO;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 面试官查询学生接口
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/pro/interview/elc_access/")
public class StudentQueryController {

    @Resource
    IStuInfoService stuInfoService;

    /**
     * 生产者获取报名者详细信息接口
     *
     * @param stuId 需要查询的报名者学号
     * @return {@link ResultVO}，其中数据为该报名者的详细信息
     */
    @GetMapping(value = "/detailed_apply_query/{stu_id}")
    public ResultVO<DetailedInfoDTO> detailedApplyQuery(@PathVariable("stu_id") String stuId) {
        return null;
    }

    /**
     * 生产者获取报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @param department 部门筛选（0为全选）
     * @param stuStatusCode 面试状态筛选（20为全选）
     * @return {@link ResultVO}，其中数据为该报名者的简要信息集合
     */
    @GetMapping(value = "/brief_apply_query/{page}/{limit}/{department}/{stu_status_code}")
    public ResultVO<PageDTO<BriefInfoDTO>> briefApplyQuery(@PathVariable("page") Integer page,
                                                           @PathVariable("limit") Integer limit,
                                                           @PathVariable("department") Integer department,
                                                           @PathVariable("stu_status_code") Integer stuStatusCode) {
        return null;
    }
}