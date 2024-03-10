package com.limu.mockito;

import com.limu.mockito.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InitMockOrSpyMethod2Test {
    private UserService mockUserService;
    private UserService spyUserService;

    @BeforeEach
    public void init(){
        mockUserService=Mockito.mock(UserService.class);
        spyUserService=Mockito.spy(UserService.class);
    }

    @Test
    public void test2(){
        System.out.println("Mockito.mockingDetails(mockUserService).isMock() :" + Mockito.mockingDetails(mockUserService).isMock());
        System.out.println("Mockito.mockingDetails(mockUserService).isSpy() :" + Mockito.mockingDetails(mockUserService).isSpy());

        System.out.println("Mockito.mockingDetails(spyUserService).isMock() :" + Mockito.mockingDetails(spyUserService).isMock());
        System.out.println("Mockito.mockingDetails(spyUserService).isSpy() :" + Mockito.mockingDetails(spyUserService).isSpy());
    }
}
