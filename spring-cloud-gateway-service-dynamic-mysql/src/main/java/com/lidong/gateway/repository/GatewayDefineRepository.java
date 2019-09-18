package com.lidong.gateway.repository;

import com.lidong.gateway.entity.GatewayDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayDefineRepository extends JpaRepository<GatewayDefine, String> {

    @Override
    List<GatewayDefine> findAll();

    @Override
    GatewayDefine save(GatewayDefine gatewayDefine);

    @Override
    void deleteById(String id);

    @Override
    boolean existsById(String id);
}
 

 
