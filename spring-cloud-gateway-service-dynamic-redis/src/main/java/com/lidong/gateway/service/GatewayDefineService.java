package com.lidong.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GatewayDefineService {

    Flux<RouteDefinition> findAll() throws Exception;

    Mono<Void> save(Mono<RouteDefinition> gatewayDefine) throws Exception;

    Mono<Void> deleteById(Mono<String> routeId) throws Exception;

    String loadRouteDefinition();
}
