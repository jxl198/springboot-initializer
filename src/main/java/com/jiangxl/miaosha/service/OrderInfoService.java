package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.OrderInfo;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createOrder(User user, GoodsVo goodsVo);
}
