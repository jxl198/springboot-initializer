package com.jiangxl.miaosha.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.service.GoodsService;
import com.jiangxl.miaosha.service.SeckillOrderService;
import com.jiangxl.miaosha.utils.RedisUtil;
import com.jiangxl.miaosha.vo.GoodsVo;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-08 17:46
 **/
@Service
public class MQReceiver {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    private GoodsService goodsService;

    private org.slf4j.Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    public void receive(String message) {
        System.out.println("receive message:" + message);
    }

    @RabbitListener(queues = {RabbitMQConfig.TOPIC_QUEUE1})
    public void receiveQueu1(String message) {
        System.out.println("receive message from queue1:" + message);
    }

    @RabbitListener(queues = {RabbitMQConfig.TOPIC_QUEUE2})
    public void receiveQueu2(String message) {
        System.out.println("receive message from queue2:" + message);
    }


    @RabbitListener(queues = RabbitMQConfig.SECKILL_QUEUE)
    public void seckill(String message) {
        SeckillMessage msg = JSON.parseObject(message, SeckillMessage.class);
        User user = msg.getUser();
        long goodsId = msg.getGoodsId();
        Object seckillOrder = redisUtil.get(Consts.Redis.ORDER_KEY_PREFIX + "_" + user.getId() + "_" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        seckillOrderService.doSeckill(user,goodsVo);

    }
}
