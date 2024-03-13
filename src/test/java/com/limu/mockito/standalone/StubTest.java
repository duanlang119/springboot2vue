package com.limu.mockito.standalone;

import com.limu.mockito.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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

    @Test
    public void test5(){
//        第一次返回1，第三次返回3，以后都是3
//        when(mockList.size()).thenReturn(1).thenReturn(2).thenReturn(3);
//        简单的写法，values
        when(mockList.size()).thenReturn(1,2,3);

        Assertions.assertEquals(1,mockList.size());
        Assertions.assertEquals(2,mockList.size());
        Assertions.assertEquals(3,mockList.size());
        Assertions.assertEquals(3,mockList.size());

    }
    @Test
    public void test6(){
        /*
        ** 泛型表示返回值的类型
        * 可以自己写与参数的关联函数
         */
        when(mockList.get(anyInt())).thenAnswer(new Answer<String>() {
            /**
             * 泛型表示要插桩方法的返回值类型
             */
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                // getArgument表示获取插桩方法(此处就是list.get)的第几个参数值
                Integer argument = invocation.getArgument(0, Integer.class);
                return String.valueOf(argument * 100);
            }
        });
        String result = mockList.get(3);
        Assertions.assertEquals("300",result);


    }
    @Test
    public void test7(){
        //mock对象,call real method
        when(mockUserServiceImpl.getNumber()).thenCallRealMethod();
        int number = mockUserServiceImpl.getNumber();
        Assertions.assertEquals(0,number);
        // spy对象，直接调用真实方法
        int spyrs=spyUserServiceImpl.getNumber();
        Assertions.assertEquals(0,spyrs);

        //不让spy对象返回真实值
        doReturn(1000).when(spyUserServiceImpl).getNumber();
        int spyrs1=spyUserServiceImpl.getNumber();
        Assertions.assertEquals(1000,spyrs1);


    }
    @Test
    public void test8(){
        mockList.add("one");
        //mock对象调用的写操作是没有效果的
        Assertions.assertEquals(0,mockList.size());

        mockList.clear();
        //验证过调用add方法，并且参数要一致
        verify(mockList).add("one");

        //等价于上面的verify(mockList)
        verify(mockList,times(1)).clear();

        //校验没有调用的两种方式
        verify(mockList,times(0)).get(1);
        verify(mockList,never()).get(1);
        //校验调用的最多和最少的次数
        verify(mockList,atLeast(1)).clear();
        verify(mockList,atMost(1)).clear();


    }
}
