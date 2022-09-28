package com.gdutelc.recruit.service.interfaces;

import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * 总进度推进接口
 * @author TUFSolareyes
 * @date 22/09/03
 */
public interface IOverAllProgress {

    /**
     * 总进度推进
     * @return {@link ResultVO}，不包含数据
     */
    ResultVO<Integer> overAllProgress();
}
