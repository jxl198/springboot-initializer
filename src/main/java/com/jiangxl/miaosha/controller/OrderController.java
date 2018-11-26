package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.domain.OrderInfo;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
import com.jiangxl.miaosha.service.GoodsService;
import com.jiangxl.miaosha.service.OrderInfoService;
import com.jiangxl.miaosha.utils.RedisUtil;
import com.jiangxl.miaosha.vo.GoodsVo;
import com.jiangxl.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-05 16:21
 **/
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("detail")
    @ResponseBody
    public Result<OrderDetailVo> getOrderDetail(User user, Long orderId) {
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo =orderInfoService.getById(orderId);
        if(orderInfo==null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrderInfo(orderInfo);
        long goodsId = orderInfo.getGoodsId();
        GoodsVo goodsVo =goodsService.getGoodsVoById(goodsId);
        orderDetailVo.setGoodsVo(goodsVo);
        return Result.success(orderDetailVo);
    }
}
