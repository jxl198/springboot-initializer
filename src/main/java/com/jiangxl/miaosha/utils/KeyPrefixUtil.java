package com.jiangxl.miaosha.utils;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-15 15:20
 **/
public class KeyPrefixUtil {

    public <T> String getPefix(Class<T> clazz,String prefix){
        return clazz.getSimpleName()+":"+prefix+":";
    }

}
