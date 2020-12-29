package cn.meetdevelop.linkedList;

import javafx.util.Pair;

/**
 * description: RecurLinkedList
 * date: 2020/11/18 10:15
 * author: zgy
 * version: 1.0
 */
public class RecurLinkedList<E> {

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


    private Node head;
    private int size;

    public RecurLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 在索引 index 处添加元素，索引从 0 开始
     * 整个链表可以看成头节点 + 剩余链表，那么添加元素可以看成是以某个头节点开始的链表中插入元素，索引发生相应的变化
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add Failed.Illegal index.");
        }

        head = add(head, index, e);
        size++;
    }

    private Node add(Node node, int index, E e) {

        if (index == 0) {
            return new Node(e, node);
        }

        node.next = add(node.next, index - 1, e);
        return node;
    }


    public void addFirst(E e) {
        add(0, e);
    }


    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得索引为 index 处的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add Failed.Illegal index.");
        }

        return get(head, index);
    }

    private E get(Node node, int index) {
        if (index == 0) {
            return node.element;
        }

        return get(node.next, index - 1);
    }


    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }


    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add Failed. Illegal index.");
        }

        Pair<Node, E> pair = remove(head, index);
        head = pair.getKey();
        size--;

        return pair.getValue();
    }

    private Pair<Node, E> remove(Node node, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add Failed. Illegal index.");
        }

        if (index == 0) {
            return new Pair<>(node.next, node.element);
        }

        Pair<Node, E> pair = remove(node.next, index - 1);

        node.next = pair.getKey();

        return new Pair<>(node, pair.getValue());
    }


    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (node.element.equals(e)) {
            // size 的变化需要写在这个地方，因为链表中不一定存在 e
            size--;
            return node.next;
        }

        node.next = removeElement(node.next, e);
        return node;

    }

}
