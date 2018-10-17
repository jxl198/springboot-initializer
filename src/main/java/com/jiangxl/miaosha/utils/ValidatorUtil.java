package com.jiangxl.miaosha.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangxl
 * @description 校验格式工具类
 * @date 2018-10-16 16:26
 **/
public class ValidatorUtil {
    private static final Pattern p = Pattern.compile("1\\d{10}");


    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Matcher matcher = p.matcher(mobile);
        return matcher.matches();

    }

    public static void main(String[] args) {
        System.out.println(isMobile("1370061368"));
    }
}
