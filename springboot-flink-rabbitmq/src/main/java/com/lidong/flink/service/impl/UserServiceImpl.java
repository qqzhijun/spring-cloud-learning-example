package com.lidong.flink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lidong.flink.domain.User;
import com.lidong.flink.mapper.UserMapper;
import com.lidong.flink.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
