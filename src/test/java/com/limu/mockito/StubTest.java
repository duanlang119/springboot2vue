package com.limu.mockito;

import com.limu.mockito.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StubTest {
    @Mock
    private List<String> mockList;

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    @Spy
    private UserServiceImpl spyUserServiceImpl;

    @Test
    public void test1(){
        doReturn("zero").when(mockList).get(0);
        Assertions.assertEquals("zero",mockList.get(0));

        when(mockList.get(1)).thenReturn("one");
        Assertions.assertEquals("one",mockList.get(1));
    }

    @Test
    public void test2(){
        doNothing().when(mockList).clear();
        mockList.clear();
        verify(mockList,times(1)).clear();
    }

    @Test
    public void test3(){
        when(mockUserServiceImpl.getNumber()).thenReturn(99);
        System.out.println("mockUserServiceImpl.getNumber() :"+mockUserServiceImpl.getNumber());
        when(spyUserServiceImpl.getNumber()).thenReturn(90);
        System.out.println("spyUserServiceImpl.getNumber() : "+spyUserServiceImpl.getNumber());
//  完全不走中间步骤
        doReturn(100).when(spyUserServiceImpl).getNumber();
        System.out.println("spyUserServiceImpl.getNumber() : "+spyUserServiceImpl.getNumber());
    }

    @Test
    public void test4(){
        doThrow(RuntimeException.class).when(mockList).clear();
        try{
            mockList.clear();
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }

        when(mockList.get(anyInt())).thenThrow(RuntimeException.class);

        try{
            mockList.get(4);
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }
    }
}
