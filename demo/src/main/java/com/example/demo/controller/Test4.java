package com.example.demo.controller;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test4
 * @date 2020/8/18 16:49
 */
public class Test4 {

    public static String getMsg() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("错误：" + e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String msg = getMsg();
            System.err.println(msg);
        }
    }
}
