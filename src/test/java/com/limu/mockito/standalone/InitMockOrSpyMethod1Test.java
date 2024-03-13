package com.limu.mockito.standalone;

import com.limu.mockito.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InitMockOrSpyMethod1Test {
    @Mock
    private UserService mockUserService;

    @Spy
    private UserService spyUserService;

    @Test
    public void test1(){
        System.out.println("Mockito.mockingDetails(mockUserService).isMock() :" + Mockito.mockingDetails(mockUserService).isMock());
        System.out.println("Mockito.mockingDetails(mockUserService).isSpy() :" + Mockito.mockingDetails(mockUserService).isSpy());

        System.out.println("Mockito.mockingDetails(spyUserService).isMock() :" + Mockito.mockingDetails(spyUserService).isMock());
        System.out.println("Mockito.mockingDetails(spyUserService).isSpy() :" + Mockito.mockingDetails(spyUserService).isSpy());
    }
}
