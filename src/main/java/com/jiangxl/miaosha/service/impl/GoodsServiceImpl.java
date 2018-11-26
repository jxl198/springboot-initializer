package com.jiangxl.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.dao.GoodsMapper;
import com.jiangxl.miaosha.domain.Goods;
import com.jiangxl.miaosha.service.GoodsService;
import com.jiangxl.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodsVo> queryGoodsVoList() {
        return goodsMapper.queryGoodsVoList();
    }

    @Override
    public GoodsVo getGoodsVoById(Long goodsId) {
        return goodsMapper.getGoodsVoById(goodsId);
    }
}
