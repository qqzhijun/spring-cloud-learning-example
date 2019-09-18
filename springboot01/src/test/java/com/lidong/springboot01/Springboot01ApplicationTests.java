package com.lidong.springboot01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Springboot01ApplicationTests {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testHello() {
//        EntityExchangeResult<String> stringEntityExchangeResult = webTestClient
//                // Create a GET request to test an endpoint
//                .get().uri("/hello")
//                .accept(MediaType.TEXT_PLAIN)
//                .exchange()
//                // and use the dedicated DSL to test assertions against the response
//                .expectStatus().isOk()
//                .expectBody(String.class).returnResult();
//        String responseBody = stringEntityExchangeResult.getResponseBody();
//        System.out.println(responseBody);


        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/hello")
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(String.class).consumeWith(response -> {
                      System.out.println(response.toString());
                      System.out.println(response.getResponseBody());
//                     String value1 =  jsonObject.getString("key1");
//                     System.out.println(value1);
        });


//        .isEqualTo("Hello, Spring!");
    }
}

