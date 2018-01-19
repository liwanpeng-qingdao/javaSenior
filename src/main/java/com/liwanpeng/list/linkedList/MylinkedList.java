package com.liwanpeng.list.linkedList;

import java.util.*;

/**
 *
 * Created by liwanpeng on 2018/1/15.
 */
public class MylinkedList<E> {
    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;


    public MylinkedList() {
    }

    public void  add(E e){
        linkedLast(e);
        size++;
    }

    private void linkedLast(E e) {
        Node<E> newNode = new Node<E>(last, e, null);

        System.out.println("e=="+e);

        //先保存起来last
        Node<E> l = last;

        //将最后一个last指向新元素
        last = newNode;

        //当last为空时 first指向元素 否则 将最后一个元素的next 指向新节点
        if (l ==null) {
            first = newNode;
        }else{
            l.next = newNode;
        }

    }
    /**
     * 在任意位置添加元素
     * @param index
     * @param e
     */
    public void  add(int index,E e){
        if (index<0 || index >size || e == null) {
            return;
        }
        //当是在尾部添加元素时
        if (index == size) {
            linkedLast(e);
        }else{
            Node<E> p = node(index);
            //当时在头部添加元素时
            if (index == 0) {
                Node<E> newNode = new Node<E>(null, e, p);
                p.prev = newNode;
                first = newNode;

            }else{
                //找到index节点的p的prev节点
                Node<E> pp = p.prev;
                //创建要添加的e的节点对象  并将它的前节点和后节点设置
                Node<E> newNode = new Node<E>(pp, e, p);
                //将前一个节点的下一个节点指向新节点
                pp.next = newNode;
                //将原来p节点的上一个节点设置给新节点
                p.prev = newNode;
            }

        }
        size++;
    }

    public void remove(int index) {
        Node<E> target = node(index);
        unlinked(target);
        size --;
    }
Map<String,String> ss = new HashMap<String,String>();
    private void unlinked(Node<E> p){
        //当是顶点节点时
        if (p.prev == null) {
            first = p.next;
        }else{
            p.prev.next = p.next;
        }

        //当时尾部节点时
        if (p.next == null) {
            last = p.prev;
        }else{
            p.next.prev = p.prev;
        }

    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public E get(int index) {

        if (index<0 || index >size) {
            return null;
        }

        return node(index).item;


    }

    private Node<E> node(int index) {
        //优化算法
        if (index < (size>>1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else{
            Node<E> node1 = last;
            for (int i = size -1; i > index; i--) {
                node1 = node1.prev;
            }
            return node1;
        }
    }
}
