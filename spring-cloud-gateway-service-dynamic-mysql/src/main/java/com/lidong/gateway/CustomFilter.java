package com.lidong.gateway;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @Auther: lidong
 * @Date: 2019-01-31 13:57
 * @Description:
 */
@Slf4j
@Configuration
public class CustomFilter {

    @Bean
    @Order(-1)
    public GlobalFilter requestLogFilter() {
        return (exchange, chain) -> {
                log.info("first pre filter:" + exchange.getRequest().getURI());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("third post filter finish");
            }));
        };
    }

}
