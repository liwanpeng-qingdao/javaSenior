package com.liwanpeng.current.bank.syncBank;

/**
 * 同步方法的银行，采用synchronized关键字
 * 同步是一种高开销的操作，因此应该尽量减少同步的内容。通常没有必要同步整个方法，使用synchronized代码块同步关键代码即可。
 * Created by liwanpeng on 2018/3/5.
 */
public class SyncBank {
    private int count =0;//账户余额

    //存钱
    public   void addMoney(int money){
        synchronized (this)
        {
            count +=money;
        }
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }

    //取钱
    public   void subMoney(int money){
        synchronized (this) {
            if(count-money < 0){
                System.out.println("余额不足");
                return;
            }
            count -=money;
        }
        count -=money;
        System.out.println(+System.currentTimeMillis()+"取出："+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
