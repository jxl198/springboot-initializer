package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.exception.GlobalException;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 9:54
 **/
public interface UserService extends IService<User> {
    boolean insert();

    boolean doLogin(String mobile, String password) throws GlobalException;
}
