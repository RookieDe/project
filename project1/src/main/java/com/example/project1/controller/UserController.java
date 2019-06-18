package com.example.project1.controller;

import com.example.project1.entity.User;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * user控制层
 * @Author RookieDe
 * @Date 2019/6/13 20:56
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/user",produces = "application/json;charset=utf-8")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获取列表信息
     * @return
     */
    @RequestMapping(value = "getUser")
    public List getUser(){
        List<User> list  = userService.getUser();
        return list;
    }

    //ceshi
}
