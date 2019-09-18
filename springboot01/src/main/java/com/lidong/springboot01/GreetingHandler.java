package com.lidong.springboot01;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class GreetingHandler {

	public Mono<ServerResponse> hello(ServerRequest request) {
		Map<String,Object> map = new HashMap<>();
		map.put("key1","value");
		map.put("key2","value");
		map.put("key3","value");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
			.body(BodyInserters.fromObject(map));
	}
}