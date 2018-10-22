package com.jiangxl.miaosha.config;

import com.jiangxl.miaosha.common.Consts;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-18 15:49
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = Consts.UserConstant.SESSION_EXPIRED_TIME)
public class SpringSessionConfig {
    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(Consts.UserConstant.COOKIE_TOKEN_NAME);//cookies名称
        cookieSerializer.setCookieMaxAge(Consts.UserConstant.COOKIE_EXPIREE_TIME);//过期时间(秒)
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setUseHttpOnlyCookie(true);
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }
}
