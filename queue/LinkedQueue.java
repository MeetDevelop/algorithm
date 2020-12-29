package cn.meetdevelop.queue;

/**
 * description: LinkedQueue
 * date: 2020/11/18 9:19
 * author: zgy
 * version: 1.0
 */
public class LinkedQueue<E> implements Queue<E> {

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }


    @Override
    public void enqueue(E element) {
        if (size == 0) {
            tail = new Node(element);
            head = tail;
        } else {
            tail.next = new Node(element);
            tail = tail.next;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        Node deleteNode = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        deleteNode.next = null;
        size--;
        return deleteNode.e;
    }

    @Override
    public E getFront() {
        if (size == 0) {
            throw new RuntimeException("Queue is Empty");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");

        Node cur = head;

        while (cur != null) {
            sb.append(cur.e + "->");
            cur = cur.next;
        }

        sb.append("Null tail");
        return sb.toString();
    }

    public static void main(String[] args){
        Queue<Integer> queue = new LinkedQueue<>();

        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }
}
