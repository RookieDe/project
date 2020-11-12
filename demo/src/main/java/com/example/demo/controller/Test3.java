package com.example.demo.controller;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test3
 * @date 2020/8/5 18:59
 */
public class Test3 implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.err.println(Thread.currentThread().getName() + "变量i的值为" + i);
        }
        return 0;
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        FutureTask<Integer> f = new FutureTask<Integer>(test3);

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "主线程，变量i的值为：" + i);
            if (i == 10) {
                Thread j = new Thread(f, "主线程");
                j.start();
                //增加一个线程2的实例，但是看不到结果，这与调用机制有关，与Runnable接口的run方法不同
                //一个FutureTask实例对象只能等待一个线程，所以线程2的实例对象看不到。
                new Thread(f, "主线程2").start();
            }

        }
        try {
            System.out.println("子线程返回的值为：" + f.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String[] x = {"p","a3","a2","d4","d1","d2","y"};
//        SortedMap<String,String> sortedMap = new TreeMap<String,String>();
//        for (String s : x) {
//            sortedMap.put(s,s);
//        }
//
//        Iterator<Map.Entry<String, String>> iterator = sortedMap.entrySet().iterator();
//        List<String> list =new ArrayList<>();
//        while (iterator.hasNext()){
//            Map.Entry<String, String> next = iterator.next();
//            list.add(next.getKey());
//        }
//        list.forEach(System.err::println);

    }

}
