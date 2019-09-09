package com.example.project2.controller;

import com.example.project2.redis.RedisUtils;
import com.example.project2.redis.key.DemoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author chenhongde
 * @ClassName DemoController
 * @date 2019/9/6 11:11
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/getUser")
    public String getUser(){
        try {
            Map map = new HashMap();
            map.put("username","陈鸿德");
            map.put("phone","13658637705");
            map.put("address","山东省济南市");
            map.put("birth","1996-07-31");
            map.put("idCard","371102199607315414");
            redisUtils.set(DemoKey.getByKey,"371102199607315414",map);
            return "测试数据！";
        }catch (Exception e){
            return "错误"+e.getMessage();
        }

    }

}
