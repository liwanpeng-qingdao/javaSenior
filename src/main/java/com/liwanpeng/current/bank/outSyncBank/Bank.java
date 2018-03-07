package com.liwanpeng.current.bank.outSyncBank;

/**
 * 方法同步，以银行存钱取钱为例（不考虑并发环境）
 * Created by liwanpeng on 2018/3/5.
 */
public class Bank {
    private int count =0;//账户余额
    //存钱
    public  void addMoney(int money){
        count +=money;
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }
    //取钱
    public  void subMoney(int money){
        if(count-money < 0){
            System.out.println("余额不足");
            return;
        }
        count -=money;
        System.out.println(+System.currentTimeMillis()+"取出："+money);
    }
    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
