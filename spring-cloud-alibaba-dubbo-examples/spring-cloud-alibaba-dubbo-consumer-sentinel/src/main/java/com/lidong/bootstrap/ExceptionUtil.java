package com.lidong.bootstrap;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

public class ExceptionUtil {


    public static ClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
        System.out.println("Oops: " + exception.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("custom block info");
    }
}
