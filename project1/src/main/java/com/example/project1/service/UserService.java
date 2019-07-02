package com.example.project1.service;

import com.example.project1.entity.User;

import java.util.List;

/**
 * @Author RookieDe
 * @Date 2019/6/13 20:58
 * @Version 1.0
 */
public interface UserService {

    List<User> getUser();

    void register(String username, String password);
}
