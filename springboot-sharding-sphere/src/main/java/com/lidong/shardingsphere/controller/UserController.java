package com.lidong.shardingsphere.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lidong.shardingsphere.domain.User;
import com.lidong.shardingsphere.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("user")
    public String saveUser(User user) {
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

    @GetMapping("/userlist/{pageNum}/{pageSize}")
    public IPage<User> getUser(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userService.page(page, wrapper);
    }

    @GetMapping("save")
    public String save100() {
        User user = null;
        for (int i = 0; i <1000000  ; i++) {
            user = new User();
            user.setName("test"+i);
            Integer age =(int)(Math.random()*1000);
            user.setAge(age);
            user.insert();

        }
        return "插入完毕";
    }

}
