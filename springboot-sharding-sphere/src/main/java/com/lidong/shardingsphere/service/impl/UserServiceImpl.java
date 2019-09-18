package com.lidong.shardingsphere.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lidong.shardingsphere.domain.User;
import com.lidong.shardingsphere.mapper.UserMapper;
import com.lidong.shardingsphere.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
