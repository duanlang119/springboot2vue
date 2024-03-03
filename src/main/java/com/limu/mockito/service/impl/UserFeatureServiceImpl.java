package com.limu.mockito.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limu.mockito.bean.entity.UserFeatureDO;
import com.limu.mockito.mapper.UserFeatureMapper;
import com.limu.mockito.service.UserFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserFeatureServiceImpl extends ServiceImpl<UserFeatureMapper, UserFeatureDO> implements UserFeatureService {

    @Override
    public List<UserFeatureDO> selectByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        LambdaQueryWrapper<UserFeatureDO> lqw = Wrappers.<UserFeatureDO>lambdaQuery()
                .eq(UserFeatureDO::getUserId,userId);
        return list(lqw);
    }
}