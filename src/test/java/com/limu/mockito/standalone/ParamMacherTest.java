package com.limu.mockito.standalone;

import com.limu.mockito.bean.VO.UserVO;
import com.limu.mockito.bean.req.UserUpdateReq;
import com.limu.mockito.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParamMacherTest {
    @Mock
    private UserService mockUserService;

    @Test
    public void test1(){
        //default value， null, 0
        UserVO userVO = mockUserService.selectById(1L);
        System.out.println("userVO="+userVO);

        UserUpdateReq userUpdateReq1 = new UserUpdateReq();
        int i = mockUserService.modifyById(userUpdateReq1);

        System.out.println("i = "+ i);
    }
    @Test
    public void test2(){
        UserUpdateReq userUpdateReq1 = new UserUpdateReq();
        userUpdateReq1.setId(1L);
        userUpdateReq1.setPhone("1L");

        doReturn(99).when(mockUserService).modifyById(userUpdateReq1);
        int rs1=mockUserService.modifyById(userUpdateReq1);
        int result3 = mockUserService.modifyById(userUpdateReq1);
        // 99
        System.out.println("result1 = " + rs1);
        // result3 = 99
        System.out.println("result3 = " + result3);

        UserUpdateReq userUpdateReq2 = new UserUpdateReq();
        userUpdateReq1.setId(2L);
        userUpdateReq1.setPhone("2L");

        int rs2=mockUserService.modifyById(userUpdateReq2);

        System.out.println("result2 = " + rs2);

    }

    @Test
    public void test3(){
        doReturn(99).when(mockUserService).modifyById(any(UserUpdateReq.class));
        UserUpdateReq userUpdateReq1 = new UserUpdateReq();
        userUpdateReq1.setId(1L);
        userUpdateReq1.setPhone("1L");

        doReturn(99).when(mockUserService).modifyById(userUpdateReq1);
        int rs1=mockUserService.modifyById(userUpdateReq1);

        // 99
        System.out.println("result1 = " + rs1);


        UserUpdateReq userUpdateReq2 = new UserUpdateReq();
        userUpdateReq1.setId(2L);
        userUpdateReq1.setPhone("2L");

        int rs2=mockUserService.modifyById(userUpdateReq2);

        System.out.println("result2 = " + rs2);


        UserUpdateReq userUpdateReq3 = new UserUpdateReq();

        int rs3=mockUserService.modifyById(userUpdateReq3);

        System.out.println("result3 = " + rs3);

    }

    @Test
    public void test4(){
        //default value， null, 0 list
        List<String> features= new ArrayList<>();
        mockUserService.add("Gordon's","123",features);

        verify(mockUserService,times(1)).add("Gordon's","123",features);

//        verify(mockUserService,times(1)).add(anyString(),"123",features);
        verify(mockUserService,times(1)).add(anyString(),anyString(),anyList());


    }
}
