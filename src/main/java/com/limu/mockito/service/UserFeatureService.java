package com.limu.mockito.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limu.mockito.bean.entity.UserFeatureDO;

import java.util.List;

public interface UserFeatureService extends IService<UserFeatureDO> {
    /**
     * 通过用户id查询该用户对应的特征值列表
     * @param userId 用户id
     * @return 特征值列表
     */
    List<UserFeatureDO> selectByUserId(Long userId);
}