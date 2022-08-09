package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.domain.entities.AdminList;
import com.gdutelc.recruit.mapper.AdminListMapper;
import com.gdutelc.recruit.service.IAdminListService;
import org.springframework.stereotype.Service;

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

}
