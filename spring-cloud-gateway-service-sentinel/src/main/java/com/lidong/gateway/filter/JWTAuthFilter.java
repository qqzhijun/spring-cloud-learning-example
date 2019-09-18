package com.lidong.gateway.filter;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.lidong.gateway.jwt.JWTUtil;
import com.lidong.gateway.comm.RestResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class JWTAuthFilter implements GlobalFilter, Ordered {

  @Override
  public int getOrder() {
    return -100;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String url = exchange.getRequest().getURI().getPath();

    //忽略以下url请求
    if(url.indexOf("/auth-service/login") >= 0 || url.indexOf("/auth-service/refreshToken") >= 0){
      return chain.filter(exchange);
    }

    //从请求头中取得token
    String token = exchange.getRequest().getHeaders().getFirst("Authorization");
    if(StringUtil.isEmpty(token)){
      ServerHttpResponse response = exchange.getResponse();
      response.setStatusCode(HttpStatus.OK);
      response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

      RestResponse res = new RestResponse(401, "401 未授权");
      byte[] responseByte = JSON.toJSON(res).toString().getBytes(StandardCharsets.UTF_8);

      DataBuffer buffer = response.bufferFactory().wrap(responseByte);
      return response.writeWith(Flux.just(buffer));
    }

    //请求中的token是否在redis中存在
    boolean verifyResult = JWTUtil.verify(token);
    if(!verifyResult){
      ServerHttpResponse response = exchange.getResponse();
      response.setStatusCode(HttpStatus.OK);
      response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
      RestResponse res = new RestResponse(1004, "token无效");
      byte[] responseByte = JSON.toJSON(res).toString().getBytes(StandardCharsets.UTF_8);
      DataBuffer buffer = response.bufferFactory().wrap(responseByte);
      return response.writeWith(Flux.just(buffer));
    }
    return chain.filter(exchange);
  }
}
