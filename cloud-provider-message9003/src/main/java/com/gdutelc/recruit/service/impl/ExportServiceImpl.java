package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdutelc.recruit.constant.*;
import com.gdutelc.recruit.domain.dto.AdmissionStuDTO;
import com.gdutelc.recruit.domain.entities.AdjustStuInfo;
import com.gdutelc.recruit.domain.entities.AdmissionStu;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.AdjustStuInfoMapper;
import com.gdutelc.recruit.mapper.AdmissionStuMapper;
import com.gdutelc.recruit.mapper.StuInfoMapper;
import com.gdutelc.recruit.service.interfaces.IExPeopleList;
import com.gdutelc.recruit.utils.CsvUtil;
import com.gdutelc.recruit.utils.GenericUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author TUFSolareyes
 * @date 22/10/03
 */

@Service
public class ExportServiceImpl implements IExPeopleList {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String TMP_PATH = "/usr/namelist/";

    public static final String APPLY_LIST = "apply";

    public static final String FIRST_LIST = "firstlist";

    public static final String SECOND_LIST = "secondlist";

    public static final String SECOND_LIST_ADJUST = "second_adjustlist";

    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    AdjustStuInfoMapper adjustStuInfoMapper;

    @Resource
    AdmissionStuMapper admissionStuMapper;

    @Override
    public ResultVO exportApplyList() {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process != RecruitStatusConstant.FIRST_INTERVIEW && process != RecruitStatusConstant.WRITTEN_EXAM) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        QueryWrapper<StuInfo> wrapper = new QueryWrapper<>();

        for(int k=DeptConstant.ALL;k<=DeptConstant.BELL_NETWORK_GROUP;k++) {
            if(k == DeptConstant.ALL) {
                wrapper.eq("status", StudentStatusConstant.REGISTERED);
            }else {
                wrapper.eq("status", StudentStatusConstant.REGISTERED).eq("first_dept",k);
            }
            List<StuInfo> stuInfos = stuInfoMapper.selectList(wrapper);
            if (stuInfos == null || stuInfos.size() == 0) {
                wrapper.clear();
                continue;
            }
            FileWriter fw = null;
            File file = new File(TMP_PATH + APPLY_LIST + k +".csv");
            try {
                if(file.exists()) {
                    return new ResultVO(ResultStatusCodeConstant.SUCCESS,"文件已经导出");
                }
                boolean newFile = file.createNewFile();
                if(!newFile) {
                    return new ResultVO(ResultStatusCodeConstant.FAILED,"创建文件失败");
                }
                fw = new FileWriter(file,true);
                String titleStr = CsvUtil.getObjectTitleStr(stuInfos.get(0));
                fw.write(titleStr + "\n");
                for(int i=0;i<stuInfos.size();i++) {
                    String parse = CsvUtil.getCsvStr(stuInfos.get(i));
                    fw.write(parse + "\n");
                }
                fw.close();
            } catch (IOException e) {
                return new ResultVO(ResultStatusCodeConstant.FAILED,"导出失败");
            }finally {
                if(fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            wrapper.clear();
        }

        return new ResultVO(ResultStatusCodeConstant.SUCCESS,"导出成功");
    }

    @Override
    public ResultVO exportFirstPassList() {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process != RecruitStatusConstant.SECOND_INTERVIEW) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }

        QueryWrapper<StuInfo> wrapper = new QueryWrapper<>();
        for(int k = DeptConstant.ALL;k<=DeptConstant.BELL_NETWORK_GROUP;k++) {
            if(k == DeptConstant.ALL) {
                wrapper.eq("status", StudentStatusConstant.REGISTERED);
            }else {
                wrapper.eq("status", StudentStatusConstant.REGISTERED).eq("first_dept",k);
            }
            List<StuInfo> stuInfos = stuInfoMapper.selectList(wrapper);
            if(stuInfos.size() == 0) {
                wrapper.clear();
                continue;
            }
            FileWriter fw = null;
            File file = new File(TMP_PATH + FIRST_LIST + k + ".csv");
            try {
                if(file.exists()) {
                    return new ResultVO(ResultStatusCodeConstant.SUCCESS,"文件已经导出");
                }
                boolean newFile = file.createNewFile();
                if(!newFile) {
                    return new ResultVO(ResultStatusCodeConstant.FAILED,"创建文件失败");
                }
                fw = new FileWriter(file,false);
                String titleStr = CsvUtil.getObjectTitleStr(stuInfos.get(0));
                fw.write(titleStr + "\n");
                for(int i=0;i<stuInfos.size();i++) {
                    String parse = CsvUtil.getCsvStr(stuInfos.get(i));
                    fw.write(parse + "\n");
                }
                fw.close();
            } catch (IOException e) {
                return new ResultVO(ResultStatusCodeConstant.FAILED,"导出失败");
            }finally {
                if(fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            wrapper.clear();
        }
        return new ResultVO(ResultStatusCodeConstant.SUCCESS,"导出成功");
    }

    @Override
    public ResultVO exportSecondPassList() {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process != RecruitStatusConstant.END) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        QueryWrapper<StuInfo> wrapper = new QueryWrapper<>();
        for(int k=DeptConstant.ALL;k<=DeptConstant.BELL_NETWORK_GROUP;k++) {
            if(k == DeptConstant.ALL) {
                wrapper.eq("status", StudentStatusConstant.EMPLOYMENT);
            }else {
                wrapper.eq("status", StudentStatusConstant.EMPLOYMENT).eq("first_dept",k);
            }
            List<StuInfo> stuInfos = stuInfoMapper.selectList(wrapper);
            if (stuInfos == null || stuInfos.size() == 0) {
                wrapper.clear();
                continue;
            }
            FileWriter fw = null;
            File file = new File(TMP_PATH + SECOND_LIST + k + ".csv");
            try {
                if(file.exists()) {
                    return new ResultVO(ResultStatusCodeConstant.SUCCESS,"文件已经导出");
                }
                boolean newFile = file.createNewFile();
                if(!newFile) {
                    return new ResultVO(ResultStatusCodeConstant.FAILED,"创建文件失败");
                }
                fw = new FileWriter(file,true);
                String titleStr = CsvUtil.getObjectTitleStr(stuInfos.get(0));
                fw.write(titleStr + "\n");
                for(int i=0;i<stuInfos.size();i++) {
                    String parse = CsvUtil.getCsvStr(stuInfos.get(i));
                    fw.write(parse + "\n");
                }
            } catch (IOException e) {
                return new ResultVO(ResultStatusCodeConstant.FAILED,"导出失败");
            }finally {
                if(fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            wrapper.clear();
        }
        return new ResultVO(ResultStatusCodeConstant.SUCCESS,"导出成功");
    }

    @Override
    public ResultVO exportSecondAdjustPassList() {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process != RecruitStatusConstant.END) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }
        QueryWrapper<AdjustStuInfo> wrapper = new QueryWrapper<>();

        for(int k=DeptConstant.ALL;k<=DeptConstant.BELL_NETWORK_GROUP;k++) {
            if(k == DeptConstant.ALL) {
                wrapper.eq("status", StudentStatusConstant.EMPLOYMENT);
            }else {
                wrapper.eq("status", StudentStatusConstant.EMPLOYMENT).eq("adjust_dept",k);
            }
            List<AdjustStuInfo> stuInfos = adjustStuInfoMapper.selectList(wrapper);
            if (stuInfos == null || stuInfos.size() == 0) {
                wrapper.clear();
                continue;
            }
            FileWriter fw = null;
            File file = new File(TMP_PATH + SECOND_LIST_ADJUST + k + ".csv");
            try {
                if(file.exists()) {
                    return new ResultVO(ResultStatusCodeConstant.SUCCESS,"文件已经导出");
                }
                boolean newFile = file.createNewFile();
                if(!newFile) {
                    return new ResultVO(ResultStatusCodeConstant.FAILED,"创建文件失败");
                }
                fw = new FileWriter(file,true);
                String titleStr = CsvUtil.getObjectTitleStr(stuInfos.get(0));
                fw.write(titleStr + "\n");
                for(int i=0;i<stuInfos.size();i++) {
                    String parse = CsvUtil.getCsvStr(stuInfos.get(i));
                    fw.write(parse + "\n");
                }
                fw.close();
            } catch (IOException e) {
                return new ResultVO(ResultStatusCodeConstant.FAILED,"导出失败");
            }finally {
                if(fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new ResultVO(ResultStatusCodeConstant.SUCCESS,"导出成功");
    }

    @Override
    public ResultVO exportAdmissionList() {
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.PROCESS);
        Integer process = Integer.parseInt(s);
        if(process != RecruitStatusConstant.END) {
            return new ResultVO(ResultStatusCodeConstant.FAILED,"当前状态不符合");
        }

        FileWriter fw = null;
        try {
            QueryWrapper<AdmissionStu> queryWrapper = new QueryWrapper<>();
            for(int i=DeptConstant.ALL;i<=DeptConstant.BELL_NETWORK_GROUP;i++) {
                if(i > DeptConstant.ALL) {
                    queryWrapper.eq("admission_dept",i);
                }
                List<AdmissionStu> admissionStus = admissionStuMapper.selectList(queryWrapper);
                if(admissionStus.size() == 0) {
                    queryWrapper.clear();
                    continue;
                }
                File file = new File(ExportServiceImpl.TMP_PATH + SECOND_LIST + i + ".csv");
                fw = new FileWriter(file);
                String titleStr = CsvUtil.getObjectTitleStr(admissionStus.get(0));
                fw.write(titleStr + "\n");
                for(int k=0;k<admissionStus.size();k++) {
                    String str = CsvUtil.getCsvStr(admissionStus.get(k));
                    fw.write(str + "\n");
                }
                queryWrapper.clear();
                fw.close();
            }
            return new ResultVO(ResultStatusCodeConstant.SUCCESS,"导出成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResultVO(ResultStatusCodeConstant.FAILED,"导出失败");
    }
}
