package com.lidong.turbine;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;


//浏览器地址栏依旧输入http://localhost:9012/hystrix
// 或者http://localhost:9019/hystrix ，
// 这是ribbon和feign与hystrix dashboard整合后访问的地址，
// 然后在打开的页面输入栏中填入：http://localhost:9065/turbine.stream ，
// 然后点击Monitor Stream，
// 在已经分别调用过对应方法（ http://localhost:9063/testhttp://localhost:9064/test ）
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTurbineApplication.class, args);
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}

