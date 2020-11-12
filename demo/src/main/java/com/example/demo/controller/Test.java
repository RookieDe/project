package com.example.demo.controller;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test
 * @date 2020/7/31 11:20
 */
public class Test extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.err.println("线程的名称：" + this.getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.err.println(Thread.currentThread().getName());
            if (i == 10) {
                //启动线程调用线程的start方法
                new Test().start();
                new Test().start();
            }
        }
    }
}
