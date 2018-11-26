package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 10:08
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("info")
    @ResponseBody
    public Result<User> getUserInfo(User user){
        return Result.success(user);

    }
}
