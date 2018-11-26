package com.jiangxl.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.dao.UserMapper;
import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.exception.GlobalException;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.service.UserService;
import com.jiangxl.miaosha.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 9:55
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public boolean insert() {
        User user1 = new User();
        user1.setId(1L);
        //user1.setName("jxl");
        User user2 =new User();
        user2.setId(1L);
      //  user2.setName("hell");
        userMapper.insert(user1);
        userMapper.insert(user2);
        return true;
    }

    @Override
    public User doLogin(String mobile, String password,HttpSession session) throws Exception {
        User user = userMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);

        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String computePass = MD5Util.formPassToDb(password, saltDb);
        if (!dbPass.equals(computePass)) {
            throw new GlobalException(CodeMsg.PASSWORD_NOT_VALID);

        }
        session.setAttribute(Consts.UserConstant.SESSION_TOKEN_NAME,user);
        return user;
    }


}
