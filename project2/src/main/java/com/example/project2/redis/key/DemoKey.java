package com.example.project2.redis.key;

import com.example.project2.redis.BasePrefix;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Demo
 * @date 2019/9/6 17:48
 */
public class DemoKey extends BasePrefix {


    public DemoKey(String prefix) {
        super(prefix);
    }

    public DemoKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static DemoKey getByKey = new DemoKey(60, "oauth");
}
