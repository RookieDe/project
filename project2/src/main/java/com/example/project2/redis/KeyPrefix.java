package com.example.project2.redis;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName KeyPrefix
 * @date 2019/9/6 17:37
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
