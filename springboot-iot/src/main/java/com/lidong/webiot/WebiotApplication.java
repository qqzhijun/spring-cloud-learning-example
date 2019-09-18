package com.lidong.webiot;


import com.lidong.webiot.netty.BootNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author lidong
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.lidong.webiot")
public class WebiotApplication implements CommandLineRunner {

    @Resource
    BootNettyServer bootNettyServer;


    public static void main(String[] args){
        /**
         * 启动springboot
         */
//        SpringApplication app = new SpringApplication(WebiotApplication.class);
//        app.setWebApplicationType(WebApplicationType.NONE);//不启动web服务
//        app.run(args);
        SpringApplication.run(WebiotApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        /**
         * 启动netty服务端服务
         */
        bootNettyServer.bind(7777);
    }
}
