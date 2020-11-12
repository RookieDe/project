package com.example.demo.controller;

import com.example.demo.entity.Account;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName TestThread
 * @date 2020/8/14 15:31
 */
public class TestThread extends Thread {
    private Account account;
    private double money;

    public TestThread(String name, Account account, double money) {
        super(name);
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        this.account.drawMoney(money);
        this.account.f1();
    }

    public static void main(String[] args) {
        Account account = new Account("1234567", 1000);
        new TestThread("ATM1号", account, 800).start();
        new TestThread("ATM2号", account, 800).start();
    }
}
