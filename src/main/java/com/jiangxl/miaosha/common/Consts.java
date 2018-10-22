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

        int ORDER_TIMEOUT = 1800;
        int USER_TIMEOUT = 1800;

    }

    interface UserConstant {
        String SESSION_TOKEN_NAME = "SESSION_TOKEN_NAME";
        int SESSION_EXPIRED_TIME = 1200;//半个小时
        String COOKIE_TOKEN_NAME="COOKIE_TOKEN_NAME";
        int COOKIE_EXPIREE_TIME=3600;
    }


}
