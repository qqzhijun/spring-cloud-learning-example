package com.lidong.springboot01;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {

	/**
	 * SpringMVC RestTemplate类本质上是阻塞的。因此，我们不希望在响应式应用程序中使用它。
	 * 对于响应式应用程序，
	 * Spring提供了WebClient类，它是非阻塞的。我们将使用WebClient实现来使用RESTful服务
	 */
	private WebClient client = WebClient.create("http://localhost:8080");

	/**
	 * WebClient使用响应式特性,以Mono的形式保存我们指定的URI的内容和一个函数(在getResult方法)将该内容转换为字符串。
	 * 如果我们有不同的需求，
	 * 我们可能会把它变成字符串以外的东西。
	 * 因为我们要将结果放入System.out中，所以这里需要一个字符串。
	 *
	 * WebClient也可以用来与非响应式的、阻塞的服务进行通信
	 */
	private Mono<ClientResponse> result = client.get()
			.uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchange();

	public String getResult() {
		return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
	}
}