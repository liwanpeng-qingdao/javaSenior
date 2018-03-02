package com.liwanpeng.TimerAndTimeTask;

import java.util.Timer;

/**
 * Created by liwanpeng on 2018/3/2.
 */
public class MyTimer {
    public static void main(String[] args) {
        //创建定时器
        Timer timer = new Timer();
        //启动任务
        System.out.println("20秒后启动，每隔15秒执行一次");
        timer.schedule(new DeleteFileTimeTask(),20000,15000);
    }

}
