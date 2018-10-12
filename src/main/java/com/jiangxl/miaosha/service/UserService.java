package com.jiangxl.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxl.miaosha.domain.User;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 9:54
 **/
public interface UserService extends IService<User> {
    boolean insert();
//    public User getById(Integer id);
}
