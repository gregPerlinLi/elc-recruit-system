package com.gdutelc.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdutelc.recruit.domain.entities.StuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生信息 Mapper 接口
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Mapper
@Repository
public interface StuInfoMapper extends BaseMapper<StuInfo> {

}
