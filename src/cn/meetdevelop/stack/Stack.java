package cn.meetdevelop.stack;

/**
 * description: Stack
 * date: 2020/11/14 9:16
 * author: zgy
 * version: 1.0
 */
public interface Stack<E> {

    void push(E element);

    E pop();

    E peek();

    int getSize();

    boolean isEmpty();

}
