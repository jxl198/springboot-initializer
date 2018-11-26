package com.jiangxl.miaosha.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-08 17:47
 **/
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "queue";

    public static final String EXCHANGE_NAME = "topicExchange";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";

    public static final String SECKILL_QUEUE = "seckill-queue";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);

    }

    @Bean
    public Queue seckillQueue() {
        return new Queue(SECKILL_QUEUE, true);

    }

    //Topic
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);
    }

    @Bean
    Binding bindExchangeTopicQueue1() {
        return BindingBuilder.bind(topicQueue1()).to(exchange()).with(TOPIC_QUEUE1);
    }

    @Bean
    Binding bindExchangeTopicQueue2() {
        return BindingBuilder.bind(topicQueue2()).to(exchange()).with("topic.#");
    }


}
