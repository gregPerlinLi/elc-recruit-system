package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.AdminList;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * <p>
 * 管理员列表 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface IAdminListService extends IService<AdminList> {

    /**
     * 管理员登录业务
     *
     * @param username 面试官用户名
     * @param password 密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该管理员的用户名
     */
    ResultVO<String> login(String username, String password, String sessionId);

}
