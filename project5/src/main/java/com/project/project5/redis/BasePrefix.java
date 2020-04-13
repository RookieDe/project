package com.project.project5.redis;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName BasePrefix
 * @date 2020/4/13 18:42
 */
public class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;


    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className +":"+ prefix;
    }

}
