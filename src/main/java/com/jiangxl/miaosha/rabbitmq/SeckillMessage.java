package com.jiangxl.miaosha.rabbitmq;

import com.jiangxl.miaosha.domain.User;

import java.io.Serializable;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-21 14:59
 **/
public class SeckillMessage  implements Serializable {

    private User user;
    private long goodsId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
