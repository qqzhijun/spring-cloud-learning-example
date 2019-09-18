package com.lidong.springboot01.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述
 *
 * @author lidong
 * @date 2018/7/18 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    private String mobile;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
