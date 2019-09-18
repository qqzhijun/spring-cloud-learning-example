package com.lidong.auth.controller;

import com.lidong.auth.comm.RestResponse;
import com.lidong.auth.jwt.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {


  @Autowired
  private StringRedisTemplate redisTemplate;

  /**
   * 登录认证
   * @param username 用户名
   * @param password 密码
   */
  @GetMapping("/login")
  public RestResponse login(@RequestParam String username, @RequestParam String password) {
    if("admin".equals(username) && "admin".equals(password)){
      String cusId = "1000";
      //生成token
      String token = JWTUtil.generateToken(username);

      //生成refreshToken
      String refreshToken = UUID.randomUUID().toString().replaceAll("-","");

      //数据放入redis
      redisTemplate.opsForHash().put(refreshToken, "token", token);
      redisTemplate.opsForHash().put(refreshToken, "cusId", cusId);

      //设置token的过期时间
      redisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
      Map<String,String> map = new HashMap<>();
      map.put("token",token);
      map.put("refreshToken",refreshToken);
      return new RestResponse(0, "登录成功", map);
    }else{
      return new RestResponse(1001, "用户名或密码错误");
    }
  }

  /**
   * 刷新token
   */
  @GetMapping("/refreshToken")
  public RestResponse refreshToken(@RequestParam String refreshToken) {
    String cusId = (String)redisTemplate.opsForHash().get(refreshToken, "cusId");
    if(StringUtils.isBlank(cusId)){
      return new RestResponse(1003, "刷新token失败");
    }

    //生成新的token
    String newToken = JWTUtil.generateToken(cusId);
    redisTemplate.opsForHash().put(refreshToken, "token", newToken);
    Map<String,String> map = new HashMap<>();
    map.put("token",newToken);
    map.put("refreshToken",refreshToken);
    return new RestResponse(0, "刷新token成功", map);
  }

  @GetMapping("/index")
  public String index() {
    return "spring-cloud-nacos-auth-service: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
