package com.gdutelc.recruit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.*;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IBriefAdjustInfoService;
import com.gdutelc.recruit.service.interfaces.IBriefInfoService;
import com.gdutelc.recruit.service.interfaces.IStuInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 面试官查询学生接口
 *
 * @author gregPerlinLi
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/pro/interview/elc_access")
public class StudentQueryController {

    @Resource
    IStuInfoService stuInfoService;

    @Resource
    IBriefInfoService briefInfoService;

    @Resource
    IBriefAdjustInfoService briefAdjustInfoService;

    /**
     * 获取报名者详细信息接口
     *
     * @param stuId 需要查询的报名者学号
     * @return {@link ResultVO}，其中数据为该报名者的详细信息
     */
    @GetMapping(value = "/detailed_apply_query/{stu_id}")
    public ResultVO<DetailedInfoDTO> detailedApplyQuery(@PathVariable("stu_id") String stuId) {
        DetailedInfoDTO detailedInfoDTO = stuInfoService.detailedApplyQuery(stuId);
        if ( detailedInfoDTO != null ) {
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "查询成功", detailedInfoDTO);
        }
        return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "没有该数据");
    }

    /**
     * 获取报名者简要信息集合接口
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

        PageDTO<BriefInfoDTO> pages = briefInfoService.briefApplyQuery(page, limit, department, stuStatusCode);
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                            "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                                pages);
    }

    /**
     * 根据姓名<b>模糊</b>查询学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的报名者的简要信息集合
     */
    @GetMapping(value = "/search_stu_by_name/{name}/{page}/{limit}")
    public ResultVO<PageDTO<BriefInfoDTO>> searchStuByName(@PathVariable("name") String name,
                                                           @PathVariable("page") Integer page,
                                                           @PathVariable("limit") Integer limit) {
        PageDTO<BriefInfoDTO> pages = briefInfoService.searchStuByName(name, page, limit);
        if ( pages.getTotal() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "无数据");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                pages);
    }

    /**
     * 根据学号<b>模糊</b>查询学生集合接口
     *
     * @param stuId 模糊查询的学号
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的报名者的简要信息集合
     */
    @GetMapping(value = "/search_stu_by_stu_id/{stu_id}/{page}/{limit}")
    public ResultVO<PageDTO<BriefInfoDTO>> searchStuByStuId(@PathVariable("stu_id") String stuId,
                                                            @PathVariable("page") Integer page,
                                                            @PathVariable("limit") Integer limit) {
        PageDTO<BriefInfoDTO> pages = briefInfoService.searchStuByStuId(stuId, page, limit);
        if ( pages.getTotal() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "无数据");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                pages);
    }

    /**
     * 获取调剂报名者简要信息集合接口
     *
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为该调剂报名者的简要信息集合
     */
    @GetMapping(value = "/brief_adjust_apply_query/{page}/{limit}")
    public ResultVO<PageDTO<BriefAdjustInfoDTO>> briefAdjustApplyQuery(@PathVariable("page") Integer page,
                                                                       @PathVariable("limit") Integer limit) {
        PageDTO<BriefAdjustInfoDTO> pages = briefAdjustInfoService.briefAdjustApplyQuery(page, limit);
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                            "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                                pages);
    }

    /**
     * 根据姓名<b>模糊</b>查询调剂学生集合接口
     *
     * @param name 模糊查询的名字
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的调剂报名者的简要信息集合
     */
    @GetMapping(value = "/search_adjust_stu_by_name/{name}/{page}/{limit}")
    public ResultVO<PageDTO<BriefAdjustInfoDTO>> searchAdjustStuByName(@PathVariable("name") String name,
                                                                       @PathVariable("page") Integer page,
                                                                       @PathVariable("limit") Integer limit) {
        PageDTO<BriefAdjustInfoDTO> pages = briefAdjustInfoService.searchAdjustStuByName(name, page, limit);
        if ( pages.getTotal() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "无数据");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                            "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                                pages);
    }

    /**
     * 根据学号<b>模糊</b>查询调剂学生集合接口
     *
     * @param stuId 模糊查询的学号
     * @param page 需要查询第几页
     * @param limit 每一页的列数限制
     * @return {@link ResultVO}，其中数据为查询出来的调剂报名者的简要信息集合
     */
    @GetMapping(value = "/search_adjust_stu_by_stu_id/{stu_id}/{page}/{limit}")
    public ResultVO<PageDTO<BriefAdjustInfoDTO>> searchAdjustStuByStuId(@PathVariable("stu_id") String stuId,
                                                                        @PathVariable("page") Integer page,
                                                                        @PathVariable("limit") Integer limit) {
        PageDTO<BriefAdjustInfoDTO> pages = briefAdjustInfoService.searchAdjustStuByStuId(stuId, page, limit);
        if ( pages.getTotal() == 0 ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND, "无数据");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,
                "查询成功, 共有" + pages.getTotal() + "条数据, 当前页面有" + pages.getList().size() + "条数据",
                pages);
    }


    /**
     * 获取签到列表
     * @param deptId 筛选部门代码
     * @return {@link ResultVO}，其中数据为当前已签到的学生集合
     */
    @GetMapping(value = "/getSignInList/{deptId}")
    public ResultVO<List<SignInDTO>> getSignInList(@PathVariable("deptId") Integer deptId) throws JsonProcessingException {
        return stuInfoService.getSignInList(deptId);
    }


}
