package com.jiangxl.miaosha.interceptor;

import com.alibaba.fastjson.JSON;
import com.jiangxl.miaosha.annotation.AccessLimit;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author jiangxl
 * @description
 * @date 2018-11-28 10:58
 **/
@Component
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtil redisUtil;

    public AccessLimitInterceptor(){
        System.out.println("AccessLimitInterceptor init");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-----------------prehandle-----------------");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            User user = null;
            if (needLogin) {
                Object obj = request.getSession().getAttribute(Consts.UserConstant.SESSION_TOKEN_NAME);
                if (obj == null) {
                    render(response, CodeMsg.USER_NOT_LOGIN);
                    return false;
                }
                user = (User) obj;
            }

            Integer count = redisUtil.get(Consts.Redis.ACCESS_LIMIT_COUNT + "_" + user.getId(), Integer.class);
            if (count == null) {
                redisUtil.set(Consts.Redis.ACCESS_LIMIT_COUNT + "_" + user.getId(), 1, seconds);
            } else if (count < maxCount) {
                redisUtil.incr(Consts.Redis.ACCESS_LIMIT_COUNT + "_" + user.getId(), 1);
            } else {
                render(response, CodeMsg.ACCESS_LIMIT_ERROR);
                return false;
            }

        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg msg) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(JSON.toJSONString(msg));
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
