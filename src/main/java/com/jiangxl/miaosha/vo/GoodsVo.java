package com.jiangxl.miaosha.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jiangxl.miaosha.domain.Goods;

import java.util.Date;

/**
 * @author jiangxl
 * @description 前端秒杀商品vo
 * @date 2018-10-22 15:14
 **/
public class GoodsVo extends Goods {

    private Integer stockCount;
    private Date startDate;
    private Date endTime;
    private Double seckillPrice;

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
