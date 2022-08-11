package com.gdutelc.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdutelc.recruit.domain.dto.ApplyInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author TUFSolareyes
 * @date 22/08/09
 */
@Mapper
@Repository
public interface ApplyMapper extends BaseMapper<ApplyInfoDTO> {


}
