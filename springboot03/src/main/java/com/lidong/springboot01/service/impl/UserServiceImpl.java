package com.lidong.springboot01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lidong.springboot01.domain.User;
import com.lidong.springboot01.mapper.UserMapper;
import com.lidong.springboot01.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
