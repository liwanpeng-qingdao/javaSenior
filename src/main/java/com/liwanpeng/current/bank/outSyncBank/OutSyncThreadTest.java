package com.liwanpeng.current.bank.outSyncBank;

/**
 * 测试非同步方法的测试类
 * Created by liwanpeng on 2018/3/5.
 */
public class OutSyncThreadTest {
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
