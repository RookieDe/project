package com.example.project1.service.serviceImpl;

import com.example.project1.dao.UserMapper;
import com.example.project1.entity.SelfUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户认证、权限
 *
 * @Author RookieDe
 * @Date 2019/6/25 21:48
 * @Version 1.0
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username查询用户
        SelfUserDetails user = userMapper.getUser(username);
        if (user == null) {
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }
        Set authoritiesSet = new HashSet();

        // 模拟从数据库中获取用户角色
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);

        logger.info("用户{}", username);
        return user;
    }
}
