package com.liuyao.miaosha.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyao
 * @date 2018/07/29
 */
@Configuration
public class MQConfig {
    public static final String QUEUE = "queue";

    @Bean
    public Queue queue() {
//        名称和是否要做持久化
        return new Queue(QUEUE, true);
    }
}
