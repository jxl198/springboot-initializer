package com.jiangxl.miaosha.common;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-15 15:23
 **/
public interface Consts {

    public interface Redis{
        public static final String ORDER_KEY_PREFIX="ORDER";
        public static final String USER_KEY_PREFIX="USER";

        public static final int ORDER_TIMEOUT=1800;
        public static final int USER_TIMEOUT=1800;

    }
}
