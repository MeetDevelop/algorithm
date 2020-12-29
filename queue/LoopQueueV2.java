package cn.meetdevelop.queue;

/**
 * description: LoopQueueV2
 * 不使用 Size 变量来记录数组中元素的个数，节省一个空间
 * date: 2020/11/14 9:56
 * author: zgy
 * version: 1.0
 */
public class LoopQueueV2<E> implements Queue<E> {

    private E[] elements;
    private int front;
    private int tail;

    public LoopQueueV2(int capacity) {
        // 需要浪费一个空间来区分队列满、队列空
        elements = (E[]) new Object[capacity + 1];
    }

    public LoopQueueV2() {
        this(10);
    }


    @Override
    public void enqueue(E element) {
        // 如果队列已满
        if ((tail + 1) % elements.length == front) {
            resize(2 * getCapacity());
        }

        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty.");
        }

        E element = elements[front];

        elements[front] = null;
        front = (front + 1) % elements.length;
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return element;
    }


    private void resize(int capacity) {
        E[] tmp = (E[]) new Object[capacity];

        int size = getSize();
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
        // 如果 tail >= front  此时的数组元素数量就是  tail - front
        // 如果 front > tail ，说明此时 tail 已经循环到了数组的前面，可以通过将数组分成两部分来计算数组中元素总的数量
        return front <= tail ? tail - front : tail + (elements.length - front);
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity() {
        return elements.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue : size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append("front [");

        for (int i = 0; i < getSize(); i++) {
            sb.append(elements[(front + i) % getCapacity()]);
            if (i != getSize() - 1) {
                sb.append(", ");
            }

        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueueV2<>();
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
