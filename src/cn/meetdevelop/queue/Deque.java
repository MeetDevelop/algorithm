package cn.meetdevelop.queue;

/**
 * description: Deque
 * date: 2020/11/14 11:19
 * author: zgy
 * version: 1.0
 */
public interface Deque<E> {

    void addFirst(E element);

    void addLast(E element);

    E removeFirst();

    E removeLast();

    int size();
}
