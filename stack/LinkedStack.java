package cn.meetdevelop.stack;

import cn.meetdevelop.linkedList.LinkedList;

/**
 * description: LinkedStack
 * date: 2020/11/17 11:32
 * author: zgy
 * version: 1.0
 */
public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E element) {
        linkedList.addFirst(element);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
