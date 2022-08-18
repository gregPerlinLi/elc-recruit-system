package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.RedisKeyConstant;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.entities.AdminList;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.AdminListMapper;
import com.gdutelc.recruit.service.interfaces.IAdminListService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 管理员列表 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class AdminListServiceImpl extends ServiceImpl<AdminListMapper, AdminList> implements IAdminListService {
    @Resource
    AdminListMapper adminListMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO<String> login(String username, String password, String sessionId) {
        if ( !GenericUtils.allOfNullable(username, password) ) {
            return new ResultVO<>(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION, "参数有误");
        }
        QueryWrapper<AdminList> queryWrapper = new QueryWrapper<>();
        HashMap<String, String> params = new HashMap<>(2);
        params.put("username", username);
        params.put("password", password);
        queryWrapper.allEq(params);
        AdminList adminList = adminListMapper.selectOne(queryWrapper);
        if ( adminList != null ) {
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.loginUserWith(username), sessionId, 30, TimeUnit.MINUTES);
            return new ResultVO<>(ResultStatusCodeConstant.SUCCESS, "登录成功", adminList.getUsername());
        } else {
            return new ResultVO<>(ResultStatusCodeConstant.FAILED, "用户名或密码错误");
        }
    }
}
