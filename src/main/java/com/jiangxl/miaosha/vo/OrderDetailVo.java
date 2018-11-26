package com.jiangxl.miaosha.vo;

import com.jiangxl.miaosha.domain.OrderInfo;

import java.io.Serializable;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-05 16:23
 **/
public class OrderDetailVo implements Serializable {

    private GoodsVo goodsVo;
    private OrderInfo orderInfo;

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
