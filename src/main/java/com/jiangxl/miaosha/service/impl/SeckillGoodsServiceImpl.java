package com.jiangxl.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.dao.SeckillGoodsMapper;
import com.jiangxl.miaosha.domain.SeckillGoods;
import com.jiangxl.miaosha.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper mapper;
    @Override
    public boolean updateStockById(Long id) {
       int result = mapper.updateStockById(id);
       return result>0;
    }
}
