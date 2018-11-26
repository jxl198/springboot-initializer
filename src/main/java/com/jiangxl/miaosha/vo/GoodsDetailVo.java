package com.jiangxl.miaosha.vo;

import com.jiangxl.miaosha.domain.User;

import java.io.Serializable;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-01 17:04
 **/
public class GoodsDetailVo implements Serializable {
    private User user;
    private Long remainingSeconds;
    private Integer seckillStatus;
    private GoodsVo goodsVo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(Long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public Integer getSeckillStatus() {
        return seckillStatus;
    }

    public void setSeckillStatus(Integer seckillStatus) {
        this.seckillStatus = seckillStatus;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }
}
