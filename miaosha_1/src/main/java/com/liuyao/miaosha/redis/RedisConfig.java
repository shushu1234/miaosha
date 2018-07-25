package com.liuyao.miaosha.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created By liuyao on 2018/07/15 11:06.
 */
@Component
@ConfigurationProperties(prefix = "redis") //读取配置文件中所有以redis前缀的配置
@Getter
@Setter
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
