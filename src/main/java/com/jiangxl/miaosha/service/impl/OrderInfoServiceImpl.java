package com.jiangxl.miaosha.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.dao.OrderInfoMapper;
import com.jiangxl.miaosha.domain.OrderInfo;
import com.jiangxl.miaosha.domain.SeckillOrder;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.service.OrderInfoService;
import com.jiangxl.miaosha.service.SeckillOrderService;
import com.jiangxl.miaosha.utils.RedisUtil;
import com.jiangxl.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public OrderInfo createOrder(User user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getSeckillPrice());
        orderInfo.setOrderChannel("1");
        orderInfo.setStatus("0");
        orderInfoMapper.saveOrderInfo(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrderService.save(seckillOrder);

        //放入redis
        redisUtil.set(Consts.Redis.ORDER_KEY_PREFIX+"_"+user.getId()+"_"+goodsVo.getId(), JSON.toJSONString(seckillOrder));
        return orderInfo;
    }
}
