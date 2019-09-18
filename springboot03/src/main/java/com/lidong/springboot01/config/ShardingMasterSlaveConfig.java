package com.lidong.springboot01.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @author lidong
 * @date 2018/7/18 15:06
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

    private Map<String, HikariDataSource> dataSources = new HashMap<>();

    private MasterSlaveRuleConfiguration masterSlaveRule;
}
