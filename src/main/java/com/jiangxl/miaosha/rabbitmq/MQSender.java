package com.jiangxl.miaosha.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-08 17:46
 **/
@Service
public class MQSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message ){
        amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE,JSON.toJSONString(message));
    }

    //topic

    public void sendTopic1(Object message ){
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_QUEUE1,JSON.toJSONString(message));

    }

    public void sendTopic2(Object message ){
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_QUEUE2,JSON.toJSONString(message));

    }

    public void seckillSend(Object message){
        amqpTemplate.convertAndSend(RabbitMQConfig.SECKILL_QUEUE,JSON.toJSONString(message));

    }
}
