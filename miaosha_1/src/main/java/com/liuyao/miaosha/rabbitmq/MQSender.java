package com.liuyao.miaosha.rabbitmq;

import com.liuyao.miaosha.util.BeanAndStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyao
 * @date 2018/07/29
 */
@Service
@Slf4j
public class MQSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message) {
        String msg = BeanAndStringUtil.beanToString(message);
        log.info("send message {}", msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }


}
