package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.OrderInfo;
import com.jiangxl.miaosha.domain.SeckillOrder;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.vo.GoodsVo;

import java.awt.image.BufferedImage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface SeckillOrderService extends IService<SeckillOrder> {


    OrderInfo doSeckill(User user, GoodsVo goodsVo);

    long getSeckillResult(User user,Long goodsId);

    String getPath(Long id, Long goodsId);

    boolean checkPath(long userId,long goodsId,String pathKey);

    BufferedImage createImg(Long id, Long goodsId);
}
