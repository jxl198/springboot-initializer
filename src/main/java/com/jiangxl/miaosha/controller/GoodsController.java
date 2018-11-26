package com.jiangxl.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.rabbitmq.MQSender;
import com.jiangxl.miaosha.rabbitmq.SeckillMessage;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
import com.jiangxl.miaosha.service.GoodsService;
import com.jiangxl.miaosha.service.SeckillOrderService;
import com.jiangxl.miaosha.utils.RedisUtil;
import com.jiangxl.miaosha.vo.GoodsDetailVo;
import com.jiangxl.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-18 15:20
 **/
@RequestMapping("/goods")
@Controller
public class GoodsController implements InitializingBean {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MQSender mqSender;

    @Autowired
    private SeckillOrderService seckillOrderService;

    private Map<String, Boolean> localMap = new HashMap<String, Boolean>();


    @RequestMapping("to_good_list")
    public String toGoodsList(Model model) {
        List<GoodsVo> goodsVoList = goodsService.queryGoodsVoList();
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";

    }

//    @RequestMapping("/to_detail/{goodsId}")
//    public String toGoodsDetail(Model model, User user, @PathVariable("goodsId") Long goodsId) {
//        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
//        model.addAttribute("user", user);
//        int seckillStatus = 0;//not start
//        long startAt = goodsVo.getStartDate().getTime();
//        long endAt = goodsVo.getEndTime().getTime();
//        long now = System.currentTimeMillis();
//        long remainingSeconds = (startAt - now) / 1000;
//        remainingSeconds = remainingSeconds < 0 ? 0 : remainingSeconds;
//        model.addAttribute("remainingSeconds", remainingSeconds);
//        if (startAt <= now && endAt >= now) {
//            seckillStatus = 1; // 进行中
//        } else if (endAt < now) {
//            seckillStatus = 2;//结束
//        } else {
//            seckillStatus = 0;//未开始
//        }
//        model.addAttribute("goodsVo", goodsVo);
//        model.addAttribute("seckillStatus", seckillStatus);
//
//        return "goods_detail";
//    }

    @RequestMapping("detail")
    @ResponseBody
    public Result<GoodsDetailVo> getGoodsDetail(User user, Long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setUser(user);
        int seckillStatus = 0;//not start
        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndTime().getTime();
        long now = System.currentTimeMillis();
        long remainingSeconds = (startAt - now) / 1000;
        remainingSeconds = remainingSeconds < 0 ? 0 : remainingSeconds;
        goodsDetailVo.setRemainingSeconds(remainingSeconds);
        if (startAt <= now && endAt >= now) {
            seckillStatus = 1; // 进行中
        } else if (endAt < now) {
            seckillStatus = 2;//结束
        } else {
            seckillStatus = 0;//未开始
        }
        goodsDetailVo.setGoodsVo(goodsVo);
        goodsDetailVo.setSeckillStatus(seckillStatus);

        return Result.success(goodsDetailVo);
    }

    @RequestMapping(value = "/{path}/do_sec_kill", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> doSeckill(User user, @RequestParam("goodsId") Long goodsId,
                                     Model model, @PathVariable("path") String pathKey) {
        if (user == null) {
            model.addAttribute("errorMsg", CodeMsg.USER_NOT_LOGIN.getMsg());
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        if (!seckillOrderService.checkPath(user.getId(), goodsId, pathKey)) {
            return Result.error(CodeMsg.PATH_ILLEGAL);
        }
        //查看是否卖完
        if (localMap.get(Consts.LocalConstant.SELL_OUT_PREFIX + "_" + goodsId)) {
            return Result.error(CodeMsg.STOCK_NOT_ENOUGH);
        }
        //从redis中减库存  redis incr decr可以实现原子性的递增和递减
        long stock = redisUtil.decr(Consts.Redis.STOCK_KEY_PREFIX + "_" + goodsId, 1);
        if (stock < 0) {
            localMap.put(Consts.LocalConstant.SELL_OUT_PREFIX + "_" + goodsId, true);
            model.addAttribute("errorMsg", CodeMsg.STOCK_NOT_ENOUGH.getMsg());
            return Result.error(CodeMsg.STOCK_NOT_ENOUGH);
        }
        //入rabbitmq队列
        SeckillMessage message = new SeckillMessage();
        message.setUser(user);
        message.setGoodsId(goodsId);
        mqSender.seckillSend(message);
        return Result.success(0);  //排队中
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("dd", "1.1");
        object.put("ii", "1");
        System.out.println(object.getDouble("dd"));
        System.out.println(object.getDouble("ii"));

    }

    @Override
    /**
     * 加载库存到redis
     */
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.queryGoodsVoList();
        if (!CollectionUtils.isEmpty(goodsVos)) {
            for (GoodsVo goodsVo : goodsVos) {
                localMap.put(Consts.LocalConstant.SELL_OUT_PREFIX + "_" + goodsVo.getId(), false);
                redisUtil.set(Consts.Redis.STOCK_KEY_PREFIX + "_" + goodsVo.getId(), goodsVo.getStockCount());
            }
        }


    }

    @RequestMapping("getSeckillResult")
    @ResponseBody
    public Result<Long> getSeckillResult(User user, Long goodsId) {
        //查询
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        return Result.success(seckillOrderService.getSeckillResult(user, goodsId));

    }

    @RequestMapping("get_path")
    @ResponseBody
    public Result<String> getPath(User user, Long goodsId) {
        //查询
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        return Result.success(seckillOrderService.getPath(user.getId(), goodsId));
    }


    @RequestMapping(value = "getVerifyCodeImg", method = RequestMethod.GET)
    public void getVerifyCodeImg(User user, Long goodsId, HttpServletResponse response) {
        try {
            BufferedImage bufferedImage = seckillOrderService.createImg(user.getId(), goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
