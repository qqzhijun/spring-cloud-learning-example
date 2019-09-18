package com.lidong.flink.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Classname User
 * @Description 用户实体类
 * @Author 李东
 * @Date 2019-05-26 17:24
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {
    /**
     * 主键Id
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private int age;

    /**
     * 创建时间
     */
    private Date createTime;
}
