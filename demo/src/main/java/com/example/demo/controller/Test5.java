package com.example.demo.controller;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test5
 * @date 2020/10/15 10:29
 */
public class Test5<E>  extends Thread{
    private ConcurrentLinkedDeque<String> queue = null;
    public Test5(ConcurrentLinkedDeque<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        for(int i=10;i<=30;i++){
            this.queue.remove(String.valueOf(i));
            System.out.println("删除的数据为："+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<String>();
        for(int i=1;i<=50;i++){
            queue.add(String.valueOf(i));
        }
        Iterator<String> iter = queue.iterator();
        Test5 t1 = new Test5(queue);
        t1.start();

        while(iter.hasNext()){
            System.out.println(iter.next()+"=====");
            Thread.sleep(100);
        }
    }


    public void tet(E e) {

    }
}

