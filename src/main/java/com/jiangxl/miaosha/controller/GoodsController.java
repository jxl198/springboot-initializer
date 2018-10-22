package com.jiangxl.miaosha.controller;

import com.jiangxl.miaosha.common.Consts;
import com.jiangxl.miaosha.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-18 15:20
 **/
@RequestMapping("/goods")
@Controller
public class GoodsController {

    @RequestMapping("to_good_list")
    public String toGoodsList(Model model,User user) {
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "goods_list";

    }
}
