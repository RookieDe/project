package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName LoginConroller
 * @date 2020/7/10 13:55
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login() {
        System.err.println("123");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setUserId(1);
        user.setUserName("陈坤弟弟");
        user.setPassword("陈坤弟弟密码");
        modelAndView.addObject(user);
        modelAndView.setViewName("index");

        return modelAndView;
    }


    @PostMapping("/page")
    public String page(@RequestParam("username") String username) {
        System.err.println(username);

        return null;
    }

}
