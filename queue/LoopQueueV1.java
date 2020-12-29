package cn.meetdevelop.queue;

/**
 * description: LoopQueueV1
 * 通过一个 size 来记录数组中元素的数量，可以不浪费一个空间来对元素进行存储
 * date: 2020/11/14 9:56
 * author: zgy
 * version: 1.0
 */
public class LoopQueueV1<E> implements Queue<E> {

    private E[] elements;
    private int front;
    private int tail;
    private int size;

    public LoopQueueV1(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    public LoopQueueV1() {
        this(10);
    }


    @Override
    public void enqueue(E element) {
        // 如果队列已满
        if (size == elements.length) {
            resize(2 * elements.length);
        }

        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty.");
        }

        E element = elements[front];

        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        if (size == (elements.length) / 4 && (elements.length) / 2 != 0) {
            resize((elements.length) / 2);
        }

        return element;
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
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty.");
        }
        return elements[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue : size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append("front [");

        for (int i = 0; i < size; i++) {
            sb.append(elements[(front + i) % getCapacity()]);
            if (i != size - 1) {
                sb.append(", ");
            }

        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args){
        Queue<Integer> queue = new LoopQueueV1<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);


            if (i % 3 == 0) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
