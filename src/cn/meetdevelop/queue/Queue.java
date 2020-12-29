package cn.meetdevelop.queue;

/**
 * description: Queue
 * date: 2020/11/14 9:42
 * author: zgy
 * version: 1.0
 */
public interface Queue<E> {

    void enqueue(E element);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();
}
