package com.project.project5.service;

import com.project.project5.dao.UserDao;
import com.project.project5.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName UserService
 * @date 2020/4/9 10:47
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public void save(User user){
        user.setUserId(1);
        userDao.save(user);
    }
}
