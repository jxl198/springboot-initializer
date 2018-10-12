package com.jiangxl.miaosha.result;

/**
 * @author jiangxl
 * @description
 * @date 2018-09-07 15:54
 **/
public class CodeMsg {
    private int code;
    private String msg;

    public static final CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务端异常");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
