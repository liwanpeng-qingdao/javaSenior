package com.liwanpeng.current;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwanpeng on 2018/2/8.
 * 1.wait和notify必须配合synchronized关键字使用

 2.wait方法释放锁，notify方法不释放锁
 */

public class WaitAndNotify {
    private volatile static List list = new ArrayList();

    public void add(){
        list.add("element");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final WaitAndNotify waitAndNotify = new WaitAndNotify();
        // 1 实例化出来一个 lock
        // 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
        final Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock){
                        for (int i=0;i<10;i++)
                        {
                            waitAndNotify.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了第"+(i+1)+"一个元素..");
                            Thread.sleep(500);
                            //当list的size为5的时候，唤醒一个等待这个对象锁的线程，即thread2
                            if(waitAndNotify.size() == 5){
                                System.out.println("t1已经发出通知..");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        if (waitAndNotify.size()!=5) {
                            System.out.println(Thread.currentThread().getName()+"发现list.size不是5，将释放对象锁。");
                            lock.wait();
                        }
                        //thread2被唤醒，但是并没有获取到锁，需要等thread1执行完循环体后，才会拿到锁，执行以下代码
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "被唤醒，将继续执行，直至停止。");
                        throw new RuntimeException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();
        thread1.start();
    }
}
