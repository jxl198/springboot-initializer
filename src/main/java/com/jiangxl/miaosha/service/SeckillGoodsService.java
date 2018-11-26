package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.SeckillGoods;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {

    boolean updateStockById(Long id);
}
