package com.limu.mockito.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limu.mockito.bean.VO.UserVO;
import com.limu.mockito.bean.entity.UserDO;
import com.limu.mockito.bean.entity.UserFeatureDO;
import com.limu.mockito.bean.req.UserUpdateReq;
import com.limu.mockito.mapper.UserMapper;
import com.limu.mockito.service.UserFeatureService;
import com.limu.mockito.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Resource
    private UserFeatureService userFeatureService;

    @Override
    public UserVO selectById(Long userId) {
        // 通过id查询出用户
        UserDO existedEntity = getById(userId);
        if (existedEntity == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(existedEntity, userVO);
        // 通过用户id查询出对应的特征值列表
        List<UserFeatureDO> features = userFeatureService.selectByUserId(userId);
        if (CollUtil.isEmpty(features)) {
            return userVO;
        }
        // 设置特征值列表
        List<String> featureValues = features.stream()
                .map(UserFeatureDO::getFeatureValue)
                .collect(Collectors.toList());
        userVO.setFeatureValue(featureValues);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(String username, String phone, List<String> features) {
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userDO.setPhone(phone);
        save(userDO);
        // 保存特征
        List<UserFeatureDO> featureList = features.stream().map(feature -> {
            UserFeatureDO userFeatureDO = new UserFeatureDO();
            userFeatureDO.setUserId(userDO.getId());
            userFeatureDO.setFeatureValue(feature);
            return userFeatureDO;
        }).collect(Collectors.toList());
        userFeatureService.saveBatch(featureList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyById(UserUpdateReq updateReq) {
        UserDO userToUpdate = new UserDO();
        BeanUtils.copyProperties(updateReq,userToUpdate);
        boolean result = updateById(userToUpdate);
        return result ? 1 : 0;
    }

    @Override
    public int getNumber() {
        System.out.println("getNumber");
        return 0;
    }
}
