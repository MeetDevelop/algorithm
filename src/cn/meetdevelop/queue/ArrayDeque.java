package cn.meetdevelop.queue;

/**
 * description: ArrayDeque
 * date: 2020/11/14 11:20
 * author: zgy
 * version: 1.0
 */
public class ArrayDeque<E> implements Deque<E> {

    private E[] elements;
    private int front;
    private int tail;
    private int size;


    public ArrayDeque(int capacity) {
        elements = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public ArrayDeque() {
        this(10);
    }

    @Override
    public void addFirst(E element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }

        // 向队列中添加元素时主要是找到 front 的位置
        // 因为上面已经进行了扩容操作，所以此时数组中一定有空间来存放元素
        front = front == 0 ? elements.length - 1 : front - 1;
        elements[front] = element;
        size++;
    }


    @Override
    public void addLast(E element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }

        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;

    }


    public E getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        return elements[front];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        return elements[tail - 1];
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        if (size == (elements.length / 4) && (elements.length / 2) != 0) {
            resize(elements.length / 2);
        }

        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        tail = tail == 0 ? elements.length - 1 : tail - 1;
        size--;
        E tmp = elements[tail];
        elements[tail] = null;
        if (size == (elements.length / 4) && (elements.length / 2) != 0) {
            resize(elements.length / 2);
        }

        return tmp;

    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        E[] tmp = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            tmp[i] = elements[(front + i) % elements.length];
        }

        front = 0;
        tail = size;
        elements = tmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue : size = %d, capacity = %d\n", size, capacity()));
        sb.append("front [");

        for (int i = 0; i < size; i++) {
            sb.append(elements[(front + i) % elements.length]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) deque.addLast(i);
            else deque.addFirst(i);
            System.out.println(deque);
        }

        // 之后依次删除元素
        System.out.println();

        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) deque.removeFirst();
            else deque.removeLast();
            System.out.println(deque);
        }
    }

}
