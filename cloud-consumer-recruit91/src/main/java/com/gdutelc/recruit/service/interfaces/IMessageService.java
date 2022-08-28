package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.entities.BriefPasserInfo;
import com.gdutelc.recruit.domain.entities.StuInfo;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.domain.wx.LoginInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * 消息推送模块生产者服务调用接口
 *
 * @author gregPerlinLi
 * @date 2022-08-14
 */
@Service
@FeignClient(name = "cloud-provider-message", contextId = "MessageServiceApi")
public interface IMessageService {

    /**
     * 生产者微信登录接口
     *
     * @param jsCode 微信登录code
     * @param grantType 授权类型
     * @return {@link ResultVO}，其中数据为小程序登录信息
     */
    @GetMapping(value = "/pro/elc_public/wx_login/{js_code}/{grant_type}")
    ResultVO<LoginInfo> wxLogin(@PathVariable("js_code") String jsCode, @PathVariable("grant_type") String grantType);

    /**
     * 生产者获取一面通过学生列表
     *
     * @return {@link ResultVO}，其中包含数据{@link List<StuInfo>}，和状态码和信息
     * @param dept 社团部门
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_first_interview_pass_list/{dept}")
    ResultVO<List<BriefPasserInfo>> getFirstInterviewPassList(@PathVariable("dept") Integer dept);


    /**
     * 获取电协最终录取的所有面试者名单
     *
     * @return {@link ResultVO}，其中包含数据{@link List<StuInfo>}，和状态码和信息
     * @param dept 社团部门
     */
    @GetMapping(value = "/pro/recruit/elc_access/get_final_admission_list/{dept}")
    ResultVO<List<BriefPasserInfo>> getFinalAdmissionList(@PathVariable("dept") Integer dept);
}
