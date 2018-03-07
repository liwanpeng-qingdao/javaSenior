package com.liwanpeng.blockingQueue;

import javafx.scene.paint.Stop;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用bllockingQueue实现的生产者线程
 * Created by liwanpeng on 2018/3/5.
 */
public class Producer  implements Runnable{

    /**
     * 消息队列
     */
    private BlockingQueue queue;
    /**
     * 判断生产者是否正在运行 使用了volatile关键字
         1）volatile本质是在告诉jvm当前变量在寄存器中的值是不确定的,需要从主存中读取,synchronized则是锁定当前变量,只有当前线程可以访问该变量,其他线程被阻塞住.
         2）volatile仅能使用在变量级别,synchronized则可以使用在变量,方法.
         3）volatile仅能实现变量的修改可见性,而synchronized则可以保证变量的修改可见性和原子性.
         4）volatile不会造成线程的阻塞,而synchronized可能会造成线程的阻塞.
     */
    private volatile boolean isRunning = true;
    /**
     * 模拟发送的数据
     * AtomicInteger是在使用非阻塞算法实现并发控制，在一些高并发程序中非常适合，但并不能每一种场景都适合，不同场景要使用使用不同的数值类。
     * 避免使Integer在并发环境下使用sycronized关键字
     */
    private static AtomicInteger count = new AtomicInteger();
    /**
     * 生产者线程每隔 0~1000毫秒生产一条消息
     */
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        String data = null;
        System.out.println("启动生产者线程！");

        Random random = new Random();
        try {
            while (isRunning)//只要处于运行状态，则一直生产消息
            {
                System.out.println("正在生产数据...");
                //sleep方法是Thread类里面的，主要的意义就是让当前线程停止执行，让出cpu给其他的线程，但是不会释放对象锁资源以及监控的状态，
                // 当指定的时间到了之后又会自动恢复运行状态。
                Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = "data:" + count.incrementAndGet();
                System.out.println("将数据" + data + "放入队列...");
                //offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中加入BlockingQueue，则返回失败。
                if (!queue.offer(data,2, TimeUnit.SECONDS))
                {
                    System.out.println("放入数据失败:" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        isRunning=false;
    }
}
