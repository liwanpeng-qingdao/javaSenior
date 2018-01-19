package com.liwanpeng.list.myList;

/**
 * list接口，ArryList接口也是实现了List接口，模拟一下o(^▽^)o
 * lintkedlist也是实现了这个接口
 * Created by liwanpeng on 2018/1/12.
 */
public interface MyList <T>{
    /**
     * 添加元素（尾部元素）
     * @param item
     */
    public void add(T item);

    /**
     * 添加元素，指定位置
     * @param index 指定位置
     * @param item 要添加的元素
     */
    public void add(int index, T item);

    /**
     * 更新指定位置的元素
     * @param index 指定的位置
     * @param item 要更新的元素
     */
    public void set(int index, T item);

    /**
     * 删除指定位置的元素
     * @param index 需要删除的元素的下标
     */
    public void remove(int index);

    /**
     * 删除某个元素
     * @param item 要删除的元素
     */
    public void remove(T item);

    /**
     * 清空数组
     */
    public void clear();

    /**
     * 是否包含某个元素
     * @param item 要查询的那个元素
     * @return
     */
    public boolean contains(T item);

    /**
     * 数组是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     *获取某个下标的元素
     * @param index 元素的下标
     * @return
     */
    public T get(int index);

    public int indexOf(T item);
    public int size();
}
