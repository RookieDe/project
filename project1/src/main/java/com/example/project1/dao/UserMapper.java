package com.example.project1.dao;

import com.example.project1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author RookieDe
 * @Date 2019/6/13 20:59
 * @Version 1.0
 */
@Repository
public interface UserMapper {

    List<User> getUser();
}
