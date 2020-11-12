package com.example.demo.controller;

import com.example.demo.entity.Account;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName AtmThread
 * @date 2020/8/14 14:58
 */
public class AtmThread extends Thread {

    private Account account;
    private double money;

    /**
     * @param String  atmNo 取款的ATM编号
     * @param Account account 要取款的账号
     * @param double  money 取款的金额
     */
    public AtmThread(String atmNo, Account account, double money) {
        super(atmNo);
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (this.account.getAccount() >= money) {
                System.out.println(this.getName() + "号ATM取钱成功！吐出钞票：" + this.money);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //修改余额
                this.account.setAccount(account.getAccount() - money);
                System.out.println("余额为：" + account.getAccount());
            } else {
                System.out.println("余额不足！");
            }
        }
    }


    public static void main(String[] args) {
        Account acount = new Account("1234567", 1000);
        new AtmThread("0112", acount, 800).start();
        new AtmThread("0315", acount, 800).start();

    }
}
