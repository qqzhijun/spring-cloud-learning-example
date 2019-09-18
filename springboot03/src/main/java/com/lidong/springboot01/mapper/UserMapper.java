package com.lidong.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lidong.springboot01.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
 
}