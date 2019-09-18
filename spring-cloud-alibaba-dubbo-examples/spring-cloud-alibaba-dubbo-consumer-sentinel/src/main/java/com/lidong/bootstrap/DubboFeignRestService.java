package com.lidong.bootstrap;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.lidong.dubbo.service.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


/**
 *
 */
@Component
@FeignClient(name = "${provider.application.name}",
        fallback = DubboFeignRestServiceFallback.class, configuration = FeignConfiguration.class)
@DubboTransported(protocol = "dubbo")
public interface DubboFeignRestService {

    @GetMapping(value = "/param")
    String param(@RequestParam("param") String param);

    @PostMapping("/params")
    String params(@RequestParam("b") String paramB, @RequestParam("a") int paramA);

    @PostMapping(value = "/request/body/map", produces = APPLICATION_JSON_UTF8_VALUE)
    User requestBody(@RequestParam("param") String param,
                     @RequestBody Map<String, Object> data);

    @GetMapping("/headers")
    public String headers(@RequestHeader("h2") String header2,
                          @RequestParam("v") Integer value, @RequestHeader("h") String header);

    @GetMapping("/path-variables/{p1}/{p2}")
    public String pathVariables(@RequestParam("v") String param,
                                @PathVariable("p2") String path2, @PathVariable("p1") String path1);
}
