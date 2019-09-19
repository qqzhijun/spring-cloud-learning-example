package com.lidong.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Eric Zhao
 */
@Configuration
public class GatewayConfiguration {

    /**
     * 默认实现为 DefaultBlockRequestHandler，
     * 当被限流时会返回类似于下面的错误信息：Blocked by Sentinel: FlowException
     * @return
     */
    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
                return ServerResponse.status(444)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject("SCS Sentinel block"));
            }
        };
    }

}
