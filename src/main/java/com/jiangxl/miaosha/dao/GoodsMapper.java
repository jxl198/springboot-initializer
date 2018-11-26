package com.jiangxl.miaosha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxl.miaosha.domain.Goods;
import com.jiangxl.miaosha.vo.GoodsVo;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> queryGoodsVoList();

    GoodsVo getGoodsVoById(Long goodsId);
}