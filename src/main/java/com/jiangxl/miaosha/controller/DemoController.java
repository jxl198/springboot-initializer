package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.domain.User;
import com.jiangxl.miaosha.rabbitmq.MQSender;
import com.jiangxl.miaosha.result.CodeMsg;
import com.jiangxl.miaosha.result.Result;
import com.jiangxl.miaosha.service.UserService;
import com.jiangxl.miaosha.utils.RedisUtil;
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
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MQSender sender;

    @RequestMapping("")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("mq")
    @ResponseBody
    public String mq() {
        sender.send("this is a first mq ");
        return "ok";
    }

    @RequestMapping("mq/topic")
    @ResponseBody
    public String mqTopic() {
        //sender.sendTopic1("topic1");
        sender.sendTopic2("topic2");

        return "ok";
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

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<Object> get(){
        Object value = redisUtil.get("key1");
        return Result.success(value);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> set(){
        boolean result = redisUtil.set("key2","hello,redis");
        System.out.println(redisUtil.get("key2"));
        return Result.success(result);
    }
}
