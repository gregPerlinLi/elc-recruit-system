package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.Process;

/**
 * <p>
 * 面试整体进度 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface IProcessService extends IService<Process> {

    /**
     * 获取当前面试总进度
     *
     * @return 当前面试总进度代码
     */
    Integer getNowProcess();

}
