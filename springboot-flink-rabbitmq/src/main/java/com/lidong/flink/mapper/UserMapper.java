package com.lidong.flink.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lidong.flink.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
