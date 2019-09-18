package com.lidong.dubbo.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务
 */
public interface UserService {

    boolean save(User user);

    boolean remove(Long userId);

    Collection<User> findAll();
}
