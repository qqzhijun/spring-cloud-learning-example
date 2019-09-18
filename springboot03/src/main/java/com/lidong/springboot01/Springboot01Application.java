package com.lidong.springboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.lidong.springboot01.*"})
@MapperScan("ccom.lidong.springboot01.mapper**")
public class Springboot01Application {


    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);//默认web应用
    }

}

