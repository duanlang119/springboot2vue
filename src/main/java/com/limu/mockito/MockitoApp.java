package com.limu.mockito;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.limu.mockito.mapper")
public class MockitoApp {
    public static void main(String[] args){
        SpringApplication.run(MockitoApp.class,args);
    }
}
