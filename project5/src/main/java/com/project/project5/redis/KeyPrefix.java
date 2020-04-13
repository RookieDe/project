package com.project.project5.redis;

public interface KeyPrefix {
    /**
     * 过期时间
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();

}
