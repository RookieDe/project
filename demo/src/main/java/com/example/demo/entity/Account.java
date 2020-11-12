package com.example.demo.entity;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Account
 * @date 2020/8/14 14:57
 */
public class Account {
    private String userNo;
    private double account;

    public Account(String userNo, double account) {
        this.userNo = userNo;
        this.account = account;
    }

    public synchronized void drawMoney(double money) {
        if (this.account >= money) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票为：" + money);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.account = this.account - money;
            System.out.println("余额为：" + this.account);
        } else {
            System.out.println(Thread.currentThread().getName() + "取现失败，余额不足！");
        }
    }

    public synchronized void f1() {
    }


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

}
