package com.liwanpeng.TimerAndTimeTask;

import java.util.TimerTask;

/**
 * 封装任务内容的类是TimerTask类.此类是一个抽象类,继承需要实现一个run方法.
 * Created by liwanpeng on 2018/3/2.
 */
public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("您该起床了！");
    }
}
