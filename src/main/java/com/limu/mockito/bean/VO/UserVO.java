package com.limu.mockito.bean.VO;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 电话
     */
    private String phone;
    /**
     * 用户对应的特征值列表
     */
    private List<String> featureValue;
}
