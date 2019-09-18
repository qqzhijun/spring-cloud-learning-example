package com.lidong.springboot01.controller;

import com.lidong.springboot01.domain.User;
import com.lidong.springboot01.mapper.UserMapper;
import com.lidong.springboot01.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 描述
 *
 * @author lidong
 * @date 2018/7/18 14:58
 */
@RestController
@Log4j2
public class UserController {

    @Qualifier("userService")
    @Autowired
    UserService userService;

    @PostMapping("user")
    public String saveUser(@RequestBody User user) {
        log.info(user);
        user.insert();
        return "插入完毕";
    }

    /**
     * 获取用户
     * @return
     */
    @GetMapping("/userlist")
    public List<User> getUser(){
        List<User> users = userService.list(null);
        return users;
    }
}
