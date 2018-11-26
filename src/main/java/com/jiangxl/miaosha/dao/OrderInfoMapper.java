package com.jiangxl.miaosha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxl.miaosha.domain.OrderInfo;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    Long saveOrderInfo(OrderInfo orderInfo);
}