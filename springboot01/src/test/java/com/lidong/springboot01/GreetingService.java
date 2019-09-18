package com.lidong.springboot01;

/**
 * Java 8为函数式接口引入了一个新注解@FunctionalInterface，主要用于编译级错误检查，加上该注解，
 * 当你写的接口不符合函数式接口定义的时候，编译器会报错。
 */
@FunctionalInterface
interface GreetingService
{
    void sayMessage(String message);


    default void sayHello(String message){

    }
    static void sayHello2(String message){

    }
}