package com.example.project2.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * Redis分布式锁
 *
 *  @author chenhongde
 * @ClassName RedisLock
 * @date 2019/9/9 15:00
 */
@Component
public class RedisLock {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 加锁
     * @param key  唯一标志
     * @param value  当前时间+超时时间 也就是时间戳
     * @return
     */
    public boolean lock(KeyPrefix prefix, String key, String value){

        //对应setnx命令
        if(redisUtils.setnx(prefix, key, value) == 1){
            //可以成功设置,也就是key不存在
            return true;
        }

        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = redisUtils.get(prefix, key, String.class);

        //如果锁过期
        //currentValue不为空且小于当前时间
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value
            //对应getset，如果key存在
            String oldValue =redisUtils.getSet(prefix, key, value);

            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(KeyPrefix prefix, String key,String value){
        try {
            String currentValue = redisUtils.get(prefix, key, String.class);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
                //删除key
                redisUtils.delete(prefix, key);
            }
        } catch (Exception e) {
            System.err.println("[Redis分布式锁] 解锁出现异常了: "+e.getMessage());
        }
    }

}
