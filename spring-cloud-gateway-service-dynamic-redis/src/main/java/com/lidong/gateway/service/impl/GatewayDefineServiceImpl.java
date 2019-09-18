package com.lidong.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.lidong.gateway.repository.RedisRouteDefinitionRepository;
import com.lidong.gateway.service.GatewayDefineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GatewayDefineServiceImpl implements GatewayDefineService{

    @Autowired
    RedisRouteDefinitionRepository gatewayDefineRepository;

    @Autowired
    private ApplicationEventPublisher publisher;


    @Override
    public Flux<RouteDefinition> findAll() throws Exception {
        return gatewayDefineRepository.getRouteDefinitions();
    }

    @Override
    public String loadRouteDefinition() {
        try {
            //从数据库拿到路由配置
            Flux<RouteDefinition> gatewayRouteList = findAll();
            log.info("网关配置信息：=====>"+ JSON.toJSONString(gatewayRouteList));
            gatewayRouteList.toStream().forEach(gatewayRoute -> {
                gatewayDefineRepository.save(Mono.just(gatewayRoute)).subscribe();
            });
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> gatewayDefine) throws Exception {
        return gatewayDefineRepository.save(gatewayDefine);
    }

    @Override
    public Mono<Void> deleteById( Mono<String> routeId) throws Exception {
        return gatewayDefineRepository.delete(routeId);
    }
}
