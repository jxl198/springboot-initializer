package com.jiangxl.miaosha.common;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-15 15:23
 **/
public interface Consts {

    interface Redis {
        String ORDER_KEY_PREFIX = "ORDER";
        String USER_KEY_PREFIX = "USER";
        String STOCK_KEY_PREFIX="STOCK";
        String SECKILL_OVER_KEY_PREFIX="SECKILL_OVER";

        String SECKILL_RANDOM_KEY="SECKILL_RANDOM_KEY";

        String SECKILL_VERIFY_CODE ="SECKILL_VERIFY_CODE";
        String ACCESS_LIMIT_COUNT ="ACCESS_LIMIT_COUNT";



        int ORDER_TIMEOUT = 1800;
        int USER_TIMEOUT = 1800;

        int SECKILL_RANDOM_KEY_TIME_OUT=60;

    }

    interface UserConstant {
        String SESSION_TOKEN_NAME = "SESSION_TOKEN_NAME";
        int SESSION_EXPIRED_TIME = 7200;//半个小时
        String COOKIE_TOKEN_NAME="COOKIE_TOKEN_NAME";
        int COOKIE_EXPIREE_TIME=3600;
    }

    interface LocalConstant{
        String SELL_OUT_PREFIX="SELL_OUT";
    }





}
