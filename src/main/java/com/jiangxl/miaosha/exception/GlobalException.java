package com.jiangxl.miaosha.exception;

import com.jiangxl.miaosha.result.CodeMsg;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-17 17:25
 **/
public class GlobalException extends RuntimeException {
    private CodeMsg codeMsg;

    public GlobalException() {
        super();
    }

    public GlobalException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;

    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
