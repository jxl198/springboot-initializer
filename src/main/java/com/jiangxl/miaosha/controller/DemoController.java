package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
import com.jiangxl.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiangxl
 * @description
 * @date 2018-09-07 15:37
 **/
@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "jiagnxl");
        return "hello";
    }

    @RequestMapping("/errorPage")
    @ResponseBody
    public Result error() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/insert")
    @ResponseBody
    public Result<Boolean> tx() {
       boolean result =userService.insert();
       return Result.success(result);
    }
}
