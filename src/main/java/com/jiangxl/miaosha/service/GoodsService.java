package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.Goods;
import com.jiangxl.miaosha.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface GoodsService extends IService<Goods> {

    List<GoodsVo> queryGoodsVoList();

    GoodsVo getGoodsVoById(Long goodsId);
}
