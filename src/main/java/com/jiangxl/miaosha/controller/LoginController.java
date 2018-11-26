package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.result.Result;
import com.jiangxl.miaosha.service.UserService;
import com.jiangxl.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-15 17:29
 **/
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/to_login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "do_login", method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(@Valid LoginVo loginVo, HttpSession session) throws Exception {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        User user = userService.doLogin(mobile, password,session);

        //session.setAttribute(Consts.UserConstant.SESSION_TOKEN_NAME, user);  //设置session
        return Result.success();
    }
}
