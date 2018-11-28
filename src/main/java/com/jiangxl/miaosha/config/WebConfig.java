package com.jiangxl.miaosha.config;

import com.jiangxl.miaosha.interceptor.AccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author jiangxl
 * @description  定义mvc 注入参数解析器
 * @date 2018-10-22 11:05
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserArgumentResolver resolver;

    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
       argumentResolvers.add(resolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
