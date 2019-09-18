package com.lidong.gateway.service.impl;

import com.lidong.gateway.entity.GatewayDefine;
import com.lidong.gateway.repository.GatewayDefineRepository;
import com.lidong.gateway.service.GatewayDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class GatewayDefineServiceImpl implements GatewayDefineService {
    @Autowired
    GatewayDefineRepository gatewayDefineRepository;

    @Autowired
    private GatewayDefineService gatewayDefineService;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public List<GatewayDefine> findAll() throws Exception {
        return gatewayDefineRepository.findAll();
    }

    @Override
    public String loadRouteDefinition() {
        try {
            List<GatewayDefine> gatewayDefineServiceAll = gatewayDefineService.findAll();
            if (gatewayDefineServiceAll == null) {
                return "none route defined";
            }
            for (GatewayDefine gatewayDefine : gatewayDefineServiceAll) {
                RouteDefinition definition = new RouteDefinition();
                definition.setId(gatewayDefine.getId());
                definition.setUri(new URI(gatewayDefine.getUri()));
                List<PredicateDefinition> predicateDefinitions = gatewayDefine.getPredicateDefinition();
                if (predicateDefinitions != null) {
                    definition.setPredicates(predicateDefinitions);
                }
                List<FilterDefinition> filterDefinitions = gatewayDefine.getFilterDefinition();
                if (filterDefinitions != null) {
                    definition.setFilters(filterDefinitions);
                }
                routeDefinitionWriter.save(Mono.just(definition)).subscribe();
                this.publisher.publishEvent(new RefreshRoutesEvent(this));
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public GatewayDefine save(GatewayDefine gatewayDefine) throws Exception {
        gatewayDefineRepository.save(gatewayDefine);
        return gatewayDefine;
    }

    @Override
    public void deleteById(String id) throws Exception {
        gatewayDefineRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) throws Exception {
        return gatewayDefineRepository.existsById(id);
    }
}