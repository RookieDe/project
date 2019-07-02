package com.example.project1.common.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author RookieDe
 * @Date 2019/6/29 1:31
 * @Version 1.0
 */
@Component
public class EncodePasswordUtils {

    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String ps = "123456";
        System.err.println(encodePassword(ps));
    }
}