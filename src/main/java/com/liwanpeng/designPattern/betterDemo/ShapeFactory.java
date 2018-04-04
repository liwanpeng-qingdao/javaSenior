package com.liwanpeng.designPattern.betterDemo;

/**
 * 使用反射机制可以解决每次增加一个产品时，都需要增加一个对象实现工厂的缺点
 * Created by liwanpeng on 2018/3/20.
 */
public class ShapeFactory {
    public static  <T> T getClass(Class<? extends T> clazz){
        T obj = null;

        try {
            obj = (T) Class.forName(clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
