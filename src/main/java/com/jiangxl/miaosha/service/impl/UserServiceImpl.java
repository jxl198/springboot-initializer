package com.jiangxl.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.dao.UserDao;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 9:55
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    @Transactional
    public boolean insert() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("jxl");
        User user2 =new User();
        user2.setId(1);
        user2.setName("hell");
        userDao.insert(user1);
        userDao.insert(user2);
        return true;
    }
}
