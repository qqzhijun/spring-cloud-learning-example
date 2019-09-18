package com.lidong.gateway.service;

import com.lidong.gateway.entity.GatewayDefine;

import java.util.List;

public interface GatewayDefineService {

    List<GatewayDefine> findAll() throws Exception;

    String loadRouteDefinition() throws Exception;

    GatewayDefine save(GatewayDefine gatewayDefine) throws Exception;

    void deleteById(String id) throws Exception;

    boolean existsById(String id) throws Exception;

}