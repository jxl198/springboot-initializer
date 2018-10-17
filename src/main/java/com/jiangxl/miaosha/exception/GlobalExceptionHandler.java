package com.jiangxl.miaosha.exception;

import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
import jdk.nashorn.internal.objects.Global;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jiangxl
 * @description 全局异常拦截器
 * @date 2018-10-17 17:01
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 定义拦截Exception.class的异常
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof BindException) {
            BindException be = (BindException) e;
            List<ObjectError> errorList = be.getAllErrors();
            ObjectError error = errorList.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.VALID_EXCEPTION.fillArgs(msg));

        }else if(e instanceof  GlobalException) {
            GlobalException ge = (GlobalException) e;
            return Result.error(ge.getCodeMsg());
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
