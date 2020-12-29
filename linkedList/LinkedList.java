package cn.meetdevelop.linkedList;

import sun.awt.image.ImageWatched;

/**
 * description: LinkedList
 * date: 2020/11/17 9:50
 * author: zgy
 * version: 1.0
 */
public class LinkedList<E> {


    private class Node {
        private E element;
        private Node next;

        public Node() {
        }

        public Node(E element) {
            this(element, null);
        }

        public Node(E e, Node next) {
            this.element = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null);
        size = 0;
    }


    /**
     * 获取链表中元素的个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }



    /**
     * 在指定索引位置添加元素(练习用，一般链表中不使用索引)
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }


    /**
     * 在链表尾部添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表第 index 处的元素(练习用)
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.element;
    }

    /**
     * 获得第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }


    /**
     * 查找链表中是否有元素 e
     * @param e
     * @return
     */
    public boolean contains(E e) {

        Node cur = dummyHead.next;

        while (cur != null) {
            if (cur.element.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    /**
     * 从链表中删除 index 位置的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node deleteNode = prev.next;
        prev.next = deleteNode.next;
        deleteNode.next = null;
        size--;
        return deleteNode.element;
    }

    /**
     * 删除首部元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除末尾的元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;

        while (cur != null) {
            sb.append(cur.element).append("->");
            cur = cur.next;
        }

        sb.append("NULL");

        return sb.toString();

    }

    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 100);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);


        linkedList.removeFirst();
        System.out.println(linkedList);


        linkedList.removeLast();
        System.out.println(linkedList);
    }



}

