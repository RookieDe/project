package com.example.project2.controller;

import com.example.project2.redis.RedisLock;
import com.example.project2.redis.RedisUtils;
import com.example.project2.redis.key.DemoKey;
import com.example.project2.redis.key.SeckillOrderKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhongde
 * @ClassName DemoController
 * @date 2019/9/6 11:11
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisLock redisLock;

    private static final int TIMEOUT = 120 * 1000;//超时时间 120s

    @RequestMapping("/setUser")
    public String setUser() {
        try {
            Map map = new HashMap();
            map.put("username", "陈鸿德");
            map.put("phone", "13658637705");
            map.put("address", "山东省济南市");
            map.put("birth", "1999-07-18");
            map.put("idCard", "371102199907185414");
            redisUtils.set(DemoKey.getByKey, "371102199907185414", map);
            return "测试数据！";
        } catch (Exception e) {
            return "错误" + e.getMessage();
        }
    }

    @RequestMapping("/insertOrder")
    public String insertOrder() {
        long time = System.currentTimeMillis() + TIMEOUT;
        try {
            //加锁
            if (!redisLock.lock(SeckillOrderKey.getByName, "ORDER123321", String.valueOf(time))) {
                return "很抱歉，人太多了，换个姿势再试试~~";
            }
            //模拟业务场景
            Thread.sleep(1000);
        } catch (Exception e) {
            return "错误" + e.getMessage();
        } finally {
            //解锁
            redisLock.unlock(SeckillOrderKey.getByName, "ORDER123321", String.valueOf(time));
        }

        return null;
    }

}
