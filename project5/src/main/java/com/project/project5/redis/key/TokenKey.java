package com.project.project5.redis.key;

import com.project.project5.redis.BasePrefix;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName TokenKey
 * @date 2020/4/13 18:46
 */
public class TokenKey extends BasePrefix {


    public TokenKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static TokenKey withExpire(int expire){
        return new TokenKey(expire,"token");
    }
}
