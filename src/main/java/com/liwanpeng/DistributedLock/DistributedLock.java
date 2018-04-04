package com.liwanpeng.DistributedLock;

import java.util.concurrent.TimeUnit;

/**
 * 基于zookeeper的分布式锁接口
 * Created by liwanpeng on 2018/3/15.
 */
public interface DistributedLock {
    /**获取锁，如果没有得到就等待*/
    public  void acquire()  throws Exception;
    /**
     * 获取锁，直到超时

     * @param time超时时间

     * @param unit time参数的单位

     * @return是否获取到锁

     * @throws Exception

     */

    public  boolean acquire (long time, TimeUnit unit)  throws Exception;

    /**

     * 释放锁

     * @throws Exception

     */

    public  void release()  throws Exception;
}
