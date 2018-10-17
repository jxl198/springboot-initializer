package com.jiangxl.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-15 17:05
 **/
public class MD5Util {
    private static final String SALT="1a2b3c4d";
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToForm(String password){
        System.out.println(SALT.charAt(1));
       String str=""+SALT.charAt(0)+SALT.charAt(1)+password+SALT.charAt(4)+SALT.charAt(5);
        System.out.println(str);
       return md5(str);
    }

    public static String formPassToDb(String formPass,String salt){
        String str= ""+salt.charAt(0)+salt.charAt(1)+formPass+salt.charAt(4)+salt.charAt(5);
        return md5(str);
    }

    public static String inputPassToDb(String password,String salt){
        String formPass =inputPassToForm(password);
        return formPassToDb(formPass,salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToForm("123456"));
        System.out.println(inputPassToDb("123456",SALT));  //78e077835bbb18fd071a62657443f3d9
    }
}
