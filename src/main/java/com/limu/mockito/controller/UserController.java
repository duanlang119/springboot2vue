package com.limu.mockito.controller;

import com.limu.mockito.bean.VO.UserVO;
import com.limu.mockito.bean.req.UserAddReq;
import com.limu.mockito.bean.req.UserUpdateReq;
import com.limu.mockito.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@Validated
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/selectById")
    public UserVO selectById(@NotNull Long userId){
        return userService.selectById(userId);
    }

    @PostMapping("/add")
    public String add(@RequestBody @Validated UserAddReq addReq) {
        userService.add(addReq.getUsername(),addReq.getPhone(),addReq.getFeatures());
        return "ok";
    }

    @PostMapping("/modifyById")
    public String modifyById(@RequestBody @Validated UserUpdateReq updateReq) {
        userService.modifyById(updateReq);
        return "ok";
    }
}