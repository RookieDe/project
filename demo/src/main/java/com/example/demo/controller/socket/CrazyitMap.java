package com.example.demo.controller.socket;

import java.util.*;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName CrazyitMap
 * @date 2020/10/16 14:11
 */
public class CrazyitMap<K,V> {
    //创建一个线程安全的HashMap
    public Map<K,V> map = Collections.synchronizedMap(new HashMap<K,V>());
    //根据value值来删除指定的项
    public synchronized void removeByValue(Object value){
        for(Object key : map.keySet()){
            if(map.get(key)==value){
                map.remove(key);
            }
        }
    }
    //获取所有的value组成的Set集合
    public synchronized Set<V> valueSet(){
        Set<V> set = new HashSet<V>();
        for(Object key : map.keySet()){
            set.add(map.get(key));
        }
        return set;
    }
    //根据value值找到key
    public synchronized K getKeyByValue(V value){
        for(K key : map.keySet()){
            if(map.get(key)==value){
                return key;
            }
        }
        return null;
    }
    //实现put方法，该方法不允许value值重复
    public synchronized V put(K key,V value){
        for(V val : valueSet()){
            if(val.equals(value) && val.hashCode()==value.hashCode()){
                throw new RuntimeException("Map实例中不允许有重复value值");
            }
        }
        return map.put(key, value);
    }
}

