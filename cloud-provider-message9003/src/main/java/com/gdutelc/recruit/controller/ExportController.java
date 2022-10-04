package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IExPeopleList;
import com.gdutelc.recruit.service.interfaces.IGetNameList;
import com.gdutelc.recruit.service.interfaces.IPassListService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author TUFSolareyes
 * @date 22/10/02
 */

@RestController
@RequestMapping(value = "/pro/super_admin/elc_access")
public class ExportController {

    @Resource
    private IGetNameList iGetNameList;

    @GetMapping(value = "/getApply/{deptId}")
    public ResultVO<byte[]> getApplyNameList(@PathVariable("deptId") Integer deptId) throws UnsupportedEncodingException {
        ResultVO<byte[]> applyList = iGetNameList.getApplyList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    @GetMapping(value = "/getFirst/{deptId}")
    @ResponseBody
    public ResultVO<byte[]> getFirstList(@PathVariable("deptId") Integer deptId) {
        System.out.println(deptId);
        ResultVO<byte[]> applyList = iGetNameList.getFirstPassList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    @GetMapping(value = "/getSecond/{deptId}")
    @ResponseBody
    public ResultVO<byte[]> getSecondList(@PathVariable("deptId") Integer deptId) {
        ResultVO<byte[]> applyList = iGetNameList.getSecondPassList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    @GetMapping(value = "/getSecondAdjust/{deptId}")
    @ResponseBody
    public ResultVO<byte[]> getSecondAdjustList(@PathVariable("deptId") Integer deptId) {
        ResultVO<byte[]> applyList = iGetNameList.getSecondAdjustList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

//    @GetMapping(value = "/testaaa")
//    @ResponseBody
//    public void exportApplyList() {
//        iExPeopleList.exportApplyList();
//    }
//
//    @GetMapping(value = "/testbbb")
//    @ResponseBody
//    public void exportFirstList() {
//        iExPeopleList.exportFirstPassList();
//    }
//
//    @GetMapping(value = "/testccc")
//    @ResponseBody
//    public void exportSecondList() {
//        iExPeopleList.exportSecondPassList();
//    }
//
//    @GetMapping(value = "/testddd")
//    @ResponseBody
//    public void exportAdSecondList() {
//        iExPeopleList.exportSecondAdjustPassList();
//    }
}
