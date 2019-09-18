package com.lidong.bootstrap;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 目前基本使用Feign都是与ribbon结合使用的，最重要的两个超时是连接超时ConnectTimeout和读超时ReadTimeout
 * 下面按优先级从高到低配置
 * Feign优于Ribbon的原因
 *
 */
@Configuration
public class FeignConfiguration {

    /**
     * Feign超时配置
     * @param env
     * @return
     */
    @Bean
    public static Request.Options requestOptions(ConfigurableEnvironment env) {
        return new Request.Options(10000, 10000);
    }

    @Bean
    public DubboFeignRestServiceFallback echoServiceFallback() {
        return new DubboFeignRestServiceFallback();
    }

}
