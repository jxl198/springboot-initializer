package com.jiangxl.miaosha.config;

import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jiangxl
 * @description 定义参数为User类型的解析器
 * @date 2018-10-22 11:07
 **/
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Consts.UserConstant.SESSION_TOKEN_NAME);
        if (obj != null) {
            User user = (User) obj;
            return user;
        }
        return null;
    }
}
