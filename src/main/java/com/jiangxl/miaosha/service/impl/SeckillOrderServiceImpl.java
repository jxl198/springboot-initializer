package com.jiangxl.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.dao.SeckillOrderMapper;
import com.jiangxl.miaosha.domain.OrderInfo;
import com.jiangxl.miaosha.domain.SeckillGoods;
import com.jiangxl.miaosha.domain.SeckillOrder;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.service.OrderInfoService;
import com.jiangxl.miaosha.service.SeckillGoodsService;
import com.jiangxl.miaosha.service.SeckillOrderService;
import com.jiangxl.miaosha.utils.MD5Util;
import com.jiangxl.miaosha.utils.RedisUtil;
import com.jiangxl.miaosha.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
@Service
@Transactional
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public OrderInfo doSeckill(User user, GoodsVo goodsVo) {
        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setId(goodsVo.getId());
        seckillGoods.setStockCount(goodsVo.getStockCount() - 1);
        boolean isSucess = seckillGoodsService.updateStockById(goodsVo.getId());
        if (isSucess) {
            OrderInfo orderInfo = orderInfoService.createOrder(user, goodsVo);
            return orderInfo;
        } else {
            setSeckillOver(user.getId(), goodsVo.getId());
        }
        return null;
    }

    /**
     * 0 正在排队中， -1 秒杀失败  成功返回订单编号
     */
    @Override
    public long getSeckillResult(User user, Long goodsId) {
        //查询订单
        List<SeckillOrder> seckillOrderList = seckillOrderMapper.selectList(new QueryWrapper<SeckillOrder>()
                .eq("USER_ID", user.getId()).eq("GOODS_ID", goodsId));
        if (seckillOrderList != null && seckillOrderList.size() > 0) {
            return seckillOrderList.get(0).getOrderId();
        } else {
            if (isSeckillOver(user.getId(), goodsId)) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String getPath(Long id, Long goodsId) {
        String randomKey = MD5Util.md5(UUID.randomUUID() + "12345");
        redisUtil.set(Consts.Redis.SECKILL_RANDOM_KEY + "_" + id + "_" + goodsId, randomKey, Consts.Redis.SECKILL_RANDOM_KEY_TIME_OUT);
        return randomKey;
    }

    public void setSeckillOver(long userId, Long id) {
        redisUtil.set(Consts.Redis.SECKILL_OVER_KEY_PREFIX + ":" + userId + ":" + id, "true");
    }

    public boolean isSeckillOver(long userId, long goodsId) {
        return redisUtil.hasKey(Consts.Redis.SECKILL_OVER_KEY_PREFIX + ":" + userId + ":" + goodsId);
    }

    @Override
    public boolean checkPath(long userId, long goodsId, String pathKey) {
        Object randomKey = redisUtil.get(Consts.Redis.SECKILL_RANDOM_KEY + "_" + userId + "_" + goodsId);
        if (randomKey != null && StringUtils.equals(randomKey.toString(), pathKey)) {
            return true;
        }
        return false;

    }

    @Override
    public BufferedImage createImg(Long id, Long goodsId) {
        height = 30;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(Color.black);
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
            graphics.drawOval(x, y, 0, 0);
        }
//        int red = 0;
//        int green = 0;
//        int black = 0;
        String verifyCode = generateVerifyCode(random);
        graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        graphics.drawString(verifyCode, 8, 13);
        graphics.dispose();
        try {
            ScriptEngineManager manage = new ScriptEngineManager();
            javax.script.ScriptEngine engine = manage.getEngineByName("javascript");
            Object result = engine.eval(verifyCode);
            redisUtil.set(Consts.Redis.SECKILL_VERIFY_CODE + "_" + id + "_" + goodsId, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bufferedImage;

    }

    private static char[] chars = {'+', '-', '*'};

    private static int width = 100;
    private static int height = 20;

    private String generateVerifyCode(Random random) {
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        int third = random.nextInt(10);
        char firstOp = chars[random.nextInt(3)];
        char sendOp = chars[random.nextInt(3)];
        return "" + first + firstOp + second + sendOp + third;


    }


}
