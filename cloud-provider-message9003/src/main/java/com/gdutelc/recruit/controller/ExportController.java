package com.gdutelc.recruit.controller;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IGetNameList;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @author TUFSolareyes
 * @date 22/10/02
 */
@RestController
@RequestMapping(value = "/pro/super_admin/elc_access")
public class ExportController {

    @Resource
    private IGetNameList iGetNameList;

    /**
     * 获取报名名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     * @throws UnsupportedEncodingException 不支持的编码异常
     */
    @GetMapping(value = "/getApply/{deptId}")
    public ResultVO<byte[]> getApplyNameList(@PathVariable("deptId") Integer deptId) throws UnsupportedEncodingException {
        ResultVO<byte[]> applyList = iGetNameList.getApplyList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    /**
     * 获取第一次面试通过名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    @GetMapping(value = "/getFirst/{deptId}")
    @ResponseBody
    public ResultVO<byte[]> getFirstList(@PathVariable("deptId") Integer deptId) {
        System.out.println(deptId);
        ResultVO<byte[]> applyList = iGetNameList.getFirstPassList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    /**
     * 获取第二次面试通过名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
    @GetMapping(value = "/getSecond/{deptId}")
    @ResponseBody
    public ResultVO<byte[]> getSecondList(@PathVariable("deptId") Integer deptId) {
        ResultVO<byte[]> applyList = iGetNameList.getSecondPassList(deptId);
        byte[] data = applyList.getData();
        return new ResultVO<>(ResultStatusCodeConstant.SUCCESS,"传输成功",data);
    }

    /**
     * 获取第二次面试调剂名单
     *
     * @param deptId 部门ID
     * @return {@link ResultVO}，其中数据为字节流
     */
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
