package com.lidong.gateway;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Auther: lidong
 * @Date: 2019-01-31 14:23
 * @Description:
 */
@Component
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    public Mono<ServerResponse> onError(Throwable throwable) {
        ServerResponse serverResponse = ServerResponse.badRequest().body(t -> {
            t.onNext("出错啦" + throwable.getMessage());
            t.onComplete();
        }, String.class).block();
        return Mono.just(serverResponse);
    }
}