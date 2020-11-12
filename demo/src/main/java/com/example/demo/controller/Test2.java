package com.example.demo.controller;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test2
 * @date 2020/7/31 11:31
 */
public class Test2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程的名称：" + Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName());
            if (i == 10) {
                //启动线程调用线程类的start()方法
                Test2 test2 = new Test2();
                new Thread(test2, "线程1").start();
                new Thread(test2, "线程2").start();
            }
        }
    }
}
