package com.liwanpeng.list.arryList;
import com.liwanpeng.list.myList.MyList;
/**
 * 实现MyList接口
 * Created by liwanpeng on 2018/1/12.
 */
public class MyArryList<T> implements MyList<T> {
    /**
     * 初始化长度
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 元素容器，是一个对象数组
     */
    private Object[] mList = null;

    /** 当前的容量 */
    private int mCurrentCapacity = 0;

    /** 容器中元素的个数 */
    private int mSize = 0;

    /**空的元素容器数组，比如用户输入的初始化长度为0*/
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 无参构造方法
     */
    public MyArryList(){
        /** 默认 元素容器数组的长度为10*/
        mList = new Object [DEFAULT_CAPACITY];
        /** 默认当前的容量为也是10*/
        mCurrentCapacity = DEFAULT_CAPACITY;
    }

    /**
     *根据用户需要构造
     */
    public MyArryList(int initCapacity){
        if (initCapacity > 0) {
            /** 大于0的时候新建数组，长度设置为用户输入的长度*/
            this.mList = new Object[initCapacity];
        } else if (initCapacity == 0) {
            /**输入长度为0的时候，直接用空数组*/
            this.mList = EMPTY_ELEMENTDATA;
        } else {
            /**抛出初始化长度参数输入错误的异常*/
            throw new IllegalArgumentException("初始化的长度错误: "+
                    initCapacity);
        }
    }

    /**
     * 添加元素的方法到数组的尾部
     * @param item
     */
    @Override
    public void add(T item) {
        //如果当前容量和实际容量相等了则需要扩容
        if (mSize == mCurrentCapacity)
        {
            expansion();
        }
        //然后给数组容器的mSize位置赋值，因为需要在当前下标+1处赋值
        mList[mSize] = item;
        //容器中实际元素的个数+1
        mSize++;
    }

    /**
     * 插入一个元素到指定位置，从插入位置及其后面的元素往后移动一个位置
     * @param index 要插入的位置
     * @param item
     * */
    @Override
    public void add(int index, T item) {
        //如果下标小于零或者大于当前数组当前的元素个数，抛出异常
        if (index<0 || index>mSize)
        {
            throw new IndexOutOfBoundsException();
        }
        //如果等于当前数组中的元素个数则需要扩容，默认扩容为当前容量的1.5倍
        if (mSize == mCurrentCapacity) {
            expansion();
        }
        //新声明一个容量为当前容量的对象数组，用于拼接
        Object[] newList = new Object[mCurrentCapacity];
        //将要插入的位置之前的数组全部复制到newList中，
        /**
         * 例如 index=2
         * mList = {1, 2, 3, 4, 5, 6};
         newList= new int[9];
         * 第一个复制完毕为：
         复制完毕后为newList为：{1 ,2 ,0 ,0 0, 0,0,0,0}
         第二个复制完毕后为：
         newList为：{1，2，0,3,4,5,6,0,0}
         可以看到中间的0为空出来让item插入的
         */
        System.arraycopy(mList, 0, newList, 0, index);
        System.arraycopy(mList, index, newList, index + 1, mSize - index);
        //将item插入下标为2的数组
        newList[index] = item;
        mList = newList;//新数组重新赋值给mList
        //同样实际容量需要+1
        mSize++;
    }
    /**
     * 更新指定位置的元素
     * @param index
     * @param item
     * */
    @Override
    public void set(int index, T item) {
        //首先判断参数的合法性
        if (index < 0 || index >= mSize) {
            throw new IndexOutOfBoundsException("下标越界了兄弟！");
        }
        mList[index]=item;//将实际数组的下标为index的元素替换掉

    }

    /**
     * 移除指定位置的元素，后面的元素向前移动一位
     * @param index
     * */
    @Override
    public void remove(int index) {
        //首先判断参数的合法性
        if (index < 0 || index >= mSize) {
            throw new IndexOutOfBoundsException("下标越界了兄弟！");
        }
        //同样使用system.arryCopy()方法
        /**
         * 1：复制从0开始长度为index的数组
         * 2：复制从index + 1到最后的数组，那么要删除的那个元素正好没有被复制，即下标为index的元素被删除
         */
        Object[] newList = new Object[mCurrentCapacity];
        System.arraycopy(mList,0,newList,0,index );
        System.arraycopy(mList, index + 1, newList, index, mSize - index);
        mList=newList;
        //数组的实际容量-1
        mSize--;
    }
    /**
     * 移除链表中特定的元素。（如果item在链表中有多个，只移除第一个）
     * @param item
     * */
    @Override
    public void remove(T item) {
        //遍历所有的元素，equals对比
        for (int i = 0; i < mSize; i++) {
            if (mList[i].equals(item)) {
                remove(i);
                break;
            }
        }
    }
    /**
     * 将链表清空，capacity不变（数组的容量没变）
     * */
    @Override
    public void clear() {
        //mList初始化
        mList = new Object[mCurrentCapacity];
        //实际元素个数为0
        mSize = 0;
    }
    /**
     * 判断是否包含某个元素
     * 需要重写元素的equals方法
     * @param item
     * @return true表示有这个元素，false表示没有这个元素
     * */
    @Override
    public boolean contains(T item) {
        for (int i = 0; i < mSize; i++) {
            if (mList[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断链表是否为空
     * @return boolean
     * */
    @Override
    public boolean isEmpty() {
        return (mSize == 0) ? true : false;
    }
    /**
     * 获取指定位置的元素
     * @param index
     * @return
     * */
    @Override
    public T get(int index) {
        //首先判断参数的合法性
        if (index < 0 || index >= mSize) {
            throw new IndexOutOfBoundsException("下标越界了兄弟！");
        }
        return (T)mList[index];
    }
    /**
     * 获取特定元素所在的位置。
     * 如果该链表中存在多个相同的元素，只返回第一个的位置，如果找不到，则返回-1。
     * @param item
     * @return int 如果没找到，返回-1
     * */
    @Override
    public int indexOf(T item) {
        for (int i = 0; i < mSize; i++) {
            if (mList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 获取当前链表的长度
     * @return int
     * */
    @Override
    public int size() {
        return mSize;
    }

    /**
     * 扩容，当 mSize == mCurrentCapacity 时调用
     * */
    private void expansion() {
        Object[] oldList = mList;
        Object[] newList = new Object[getNewCapacity()];
        /**
         *
         * src:源数组；
         srcPos:源数组要复制的起始位置；
         dest:目的数组；
         destPos:目的数组放置的起始位置；
         length:复制的长度。
         注意：src and dest都必须是同类型或者可以进行转换类型的数组．
         这个语句意思是复制oldList的0—oldList.length的元素，放进newList中，开始放置的位置是0，也就是将老数组复制一份到
         新的已经扩容的数组中。
         */
        System.arraycopy(oldList, 0, newList, 0, oldList.length);
        mList = newList;
    }
    /**
     * 获取新的容量大小
     * 当满的时候每次增加当前容量的50%,也就是变为当前长度的1.5倍
     * */
    private int getNewCapacity() {
        //>>1位运算，相当于1.5倍
        return mCurrentCapacity = mCurrentCapacity + (mCurrentCapacity >> 1);
    }
}
