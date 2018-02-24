package com.liwanpeng.current;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ：JDK1.5后是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。主要代码由大牛Doug Lea完成
 * 比如对于马拉松比赛，进行排名计算，参赛者的排名，肯定是跑完比赛之后，进行计算得出的，翻译成Java识别的预发，
 * 就是N个线程执行操作，主线程等到N个子线程执行完毕之后，在继续往下执行。
 *
 * 主要方法

 await(); // 使当前线程在锁存器倒计数至零之前一直等待
 countDown(); // 递减锁存器的计数，如果计数到达零，则释放所有等待的线程


 * Created by liwanpeng on 2018/2/24.
 */
public class CountDownLatchTest {
    final List list = new ArrayList();
    public void add(){
        list.add("element");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<10;i++)
                {
                    countDownLatchTest.add();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了第"+(i+1)+"一个元素..");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (countDownLatchTest.size()==5)
                    {
                        System.out.println("thread2已经发出通知！");
                        countDownLatch.countDown();
                    }
                }
                System.out.println("thread2停止！");
            }
        },"thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (countDownLatchTest.size()!=5)
                {
                    System.out.println("thread2不再往下执行，等待thread1执行");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                }
            }
        },"thread2");
        thread2.start();
        thread1.start();
    }
}
