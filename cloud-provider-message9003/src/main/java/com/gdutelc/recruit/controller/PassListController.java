package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.dto.AdmissionStuDTO;
import com.gdutelc.recruit.domain.dto.DetailedInfoDTO;
import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author cherry_jerry
 * @date 2022/08/28 14:24
 */
@RestController
@RequestMapping("/pro/super_admin/elc_access")
public class PassListController {

    @Resource
    private IPassListService passListService;
    /**
     * 获取一面通过的学生列表
     *
     * @param dept 部门筛选（{@code 0}代表不筛选部门）
     * @return {@link ResultVO}，其中包含数据{@link List < StuInfo >}，和状态码和信息
     */
    @GetMapping(value = "/get_first_interview_pass_list/{dept}")
    public ResultVO<List<BriefPasserInfo>> getFirstInterviewPassList(@PathVariable("dept") Integer dept) {
        List<BriefPasserInfo> passList = passListService.getFirstInterviewPassList(dept);
        if(passList == null || passList.isEmpty()){
            return  new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无记录");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"获取成功",passList);
    }

    /**
     * 获取最终录用学生列表
     *
     * @param dept 部门筛选（{@code 0}代表不筛选部门）
     * @return {@link ResultVO}，其中包含数据{@link List<AdmissionStuDTO>}，和状态码和信息
     */
    @GetMapping(value = "/get_final_admission_list/{dept}")
    public ResultVO<List<AdmissionStuDTO>> getFinalAdmissionList(@PathVariable("dept") Integer dept) {
        List<AdmissionStuDTO> result = passListService.getFinalAdmissionList(dept);
        if ( result == null || result.isEmpty() ) {
            return new ResultVO<>(ResultStatusCodeConstant.NOT_FIND,"无记录");
        }
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "获取成功", result);
    }
}
