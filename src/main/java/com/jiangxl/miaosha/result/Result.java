package com.jiangxl.miaosha.result;

import java.io.Serializable;

/**
 * @author jiangxl
 * @description
 * @date 2018-09-07 15:48
 **/
public class Result<T>  implements Serializable {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);

    }

    public static Result success(){
       return new Result(CodeMsg.SUCCESS);
    }

    public static <T> Result error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    private Result(CodeMsg codeMsg){
        if(codeMsg==null){
            return;
        }
        this.code= codeMsg.getCode();
        this.msg=codeMsg.getMsg();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
