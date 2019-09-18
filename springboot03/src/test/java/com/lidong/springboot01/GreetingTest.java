package com.lidong.springboot01;

import org.junit.Test;

import java.util.function.Consumer;

public class GreetingTest {

    @Test
    public void test(){
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("ddd");
    }


    @Test
    public void test2(){

        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        consumer.accept(sb);
        System.out.println(sb.toString());	// Hello Jack!
//        andThen(Consumer<? super T> after)

        Consumer<StringBuilder> consumer1 = (str) -> str.append(" Bob!");
        consumer.andThen(consumer1).accept(sb);
        System.out.println(sb.toString());	// Hello Jack! Bob!
    }
}
