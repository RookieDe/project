package com.example.project1.service.serviceImpl;

import com.example.project1.dao.UserMapper;
import com.example.project1.entity.User;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author RookieDe
 * @Date 2019/6/13 20:59
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser() {
        return userMapper.getUserList();
    }

    @Override
    public void register(String username, String password) {
        //因为只是简单注册，故只是对密码加密保存，其他就不添加进来了
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String EncryptedPassword = bCryptPasswordEncoder.encode(password);
        userMapper.register(username,EncryptedPassword);
    }

}
