package com.jiangxl.miaosha.result;

/**
 * @author jiangxl
 * @description
 * @date 2018-09-07 15:54
 **/
public class CodeMsg {
    private int code;
    private String msg;
    public static final CodeMsg SUCCESS=new CodeMsg(0,"success");

    public static final CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务端异常");
    public static final CodeMsg VALID_EXCEPTION=new CodeMsg(500101,"校验参数异常：%s");
    //    public static final CodeMsg PASSWORD_EMPTY=new CodeMsg(500101,"登录密码不能为空");
//    public static final CodeMsg MOBILE_EMPTY=new CodeMsg(500102,"手机号码不能为空");
//    public static final CodeMsg MOBILE_NOT_VALID=new CodeMsg(500103,"手机号码格式不正确");
    public static final CodeMsg USER_NOT_EXIST=new CodeMsg(500104,"该用户不存在");
    public static final CodeMsg PASSWORD_NOT_VALID=new CodeMsg(500105,"用户密码不正确");
    public static final CodeMsg USER_NOT_LOGIN=new CodeMsg(500106,"用户未登录");

    public static final CodeMsg SESSION_ERROR=new CodeMsg(500106,"session不存在或已失效");
    public static final CodeMsg PATH_ILLEGAL=new CodeMsg(500107,"请求非法");
    public static final CodeMsg ACCESS_LIMIT_ERROR=new CodeMsg(500108,"请求太频繁");



    // 秒杀错误提示5005xx
    public static final CodeMsg STOCK_NOT_ENOUGH=new CodeMsg(500501,"商品库存不足");
    public static final CodeMsg REPEAT_SECKILL=new CodeMsg(500502,"重复秒杀");

    //订单错误提示5006XX
    public static final CodeMsg ORDER_NOT_EXIST=new CodeMsg(500601,"订单不存在");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public CodeMsg fillArgs(Object... args){
        int code= this.code;
        String message = String.format(this.msg,args );
        return new CodeMsg(code,message);
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
