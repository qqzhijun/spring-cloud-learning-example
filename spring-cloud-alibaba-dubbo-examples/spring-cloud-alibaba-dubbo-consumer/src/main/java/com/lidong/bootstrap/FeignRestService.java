package com.lidong.bootstrap;

import com.lidong.dubbo.service.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Component
@FeignClient("${provider.application.name}")
public interface FeignRestService {

    @GetMapping(value = "/param")
    String param(@RequestParam("param") String param);

    @PostMapping("/params")
    public String params(@RequestParam("b") String b, @RequestParam("a") int a);

    @PostMapping(value = "/request/body/map", produces = APPLICATION_JSON_UTF8_VALUE)
    User requestBody(@RequestParam("param") String param,
                     @RequestBody Map<String, Object> data);

    @GetMapping("/headers")
    public String headers(@RequestHeader("h2") String header2,
                          @RequestHeader("h") String header, @RequestParam("v") Integer value);

    @GetMapping("/path-variables/{p1}/{p2}")
    public String pathVariables(@PathVariable("p2") String path2,
                                @PathVariable("p1") String path1, @RequestParam("v") String param);
}
