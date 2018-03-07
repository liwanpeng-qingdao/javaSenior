package com.liwanpeng.current.bank.volatileBank;

import com.liwanpeng.current.bank.outSyncBank.Bank;

/**
 * ①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法
 ②volatile只能保证数据的可见性，不能用来同步，因为多个线程并发访问volatile修饰的变量不会阻塞。
     synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。
     多个线程争抢synchronized锁对象时，会出现阻塞。
 * Created by liwanpeng on 2018/3/5.
 */
public class VolatileBankTest {
    public static void main(String args[]){
        final Bank bank=new Bank();
        //存钱线程
        Thread tadd=new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");

                }
            }
        });
        //取钱线程
        Thread tsub = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tsub.start();

        tadd.start();
    }
}
