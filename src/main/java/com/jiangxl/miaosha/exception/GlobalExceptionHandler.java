package com.jiangxl.miaosha.exception;

import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
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
public class GlobalExceptionHandler {

    /**
     * 定义全局拦截Exception.class的异常
     * 全局拦截器有两套，一个是@ExceptionHandler 配合@ControllerAdvice 使用 spring推荐这一种
     * 另外一个是实现HandlerExceptionResolve接口，需要配置@Component
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof BindException) {//校验异常
            BindException be = (BindException) e;
            List<ObjectError> errorList = be.getAllErrors();
            ObjectError error = errorList.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.VALID_EXCEPTION.fillArgs(msg));

        }else if(e instanceof  GlobalException) {
            GlobalException ge = (GlobalException) e;
            return Result.error(ge.getCodeMsg());
        } else {
            System.out.println("全局异常");
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }


}
