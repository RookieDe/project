package com.example.project2.redis.key;

import com.example.project2.redis.BasePrefix;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName SeckillOrderKey
 * @date 2019/9/9 16:11
 */
public class SeckillOrderKey extends BasePrefix {
    public SeckillOrderKey(String prefix) {
        super(prefix);
    }

    public SeckillOrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillOrderKey getByName = new SeckillOrderKey(10,"seckill");
}
